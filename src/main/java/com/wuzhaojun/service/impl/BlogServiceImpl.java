package com.wuzhaojun.service.impl;

import com.power.common.util.ObjectUtil;
import com.wuzhaojun.entity.BlogCollectionEntity;
import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.mapper.BlogCollectionMapper;
import com.wuzhaojun.mapper.BlogMapper;
import com.wuzhaojun.service.BlogService;
import com.wuzhaojun.vo.BlogCollectionVO;
import com.wuzhaojun.vo.BlogVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.power.common.util.UUIDUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:38
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogCollectionMapper blogCollectionMapper;
    @Override
    public BlogVO findAllBlog(String userName,int pageSize) {
        List<BlogEntity> blogEntities = blogMapper.blogUserPage(userName, (pageSize-1)*5);

        Example exampleCount = new Example(BlogEntity.class);
        exampleCount.createCriteria().andEqualTo("blogUser",userName);

        int count = blogMapper.selectCountByExample(exampleCount);

        BlogVO pageBlog = new BlogVO();
        pageBlog.setBlogEntities(blogEntities);
        pageBlog.setTotal(String.valueOf(count));
        return pageBlog;
    }

    @Override
    public Boolean insertBlog(BlogEntity blogEntity) {


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        blogEntity.setBlogId(UUIDUtil.getUuid32());
        blogEntity.setBlogReleaseTime(java.sql.Date.valueOf(sdf.format(date)));
        //默认不分享
        blogEntity.setBlogShare("0");
        blogEntity.setBlogShareText("未分享");

        blogEntity.setBlogCode("0");
        blogEntity.setBlogCodeText("未收藏");
        int i = blogMapper.insertSelective(blogEntity);
        if (i<=0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateBlog(BlogEntity blogEntity) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        blogEntity.setBlogUpdateTime(java.sql.Date.valueOf(sdf.format(date)));

        int i = blogMapper.updateByPrimaryKeySelective(blogEntity);
        if (i<=0){
            return false;
        }
        return true;
    }

    @Override
    public BlogEntity findIdBlog(String  id) {
        BlogEntity blogEntity = blogMapper.selectByPrimaryKey(id);

        return blogEntity;
    }

    @Override
    public Boolean deleteBlog(String id) {
        int i = blogMapper.deleteByPrimaryKey(id);
        Example example = new Example(BlogCollectionEntity.class);
        example.createCriteria().andEqualTo("blogCollectionAndUser",id);
        int j = blogCollectionMapper.deleteByExample(example);
        if (i<=0 && j<=0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean shareBlog(String id,String code) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setBlogId(id);
        if ("1".equals(code)){
            blogEntity.setBlogShare("0");
            blogEntity.setBlogShareText("已分享");
        }else {
            blogEntity.setBlogShare("1");
            blogEntity.setBlogShareText("未分享");
        }
        blogEntity.setBlogShare(code);
        int i = blogMapper.updateByPrimaryKeySelective(blogEntity);
        if (i<=0){
            return false;
        }
        return true;
    }
    /**
     * 博客分享展示和对应的用户是否收藏展示
     */
    @Override
    public BlogVO shareAllBlog(int pageSize,int size,String userName) {

        //展示已经分享的内容
        List<BlogEntity> blogEntities = blogMapper.blogPage("1", (pageSize - 1)*5, size);

        //用户收藏的全部数据
        List<BlogCollectionVO> blogCollectionVOS = blogMapper.blogCollection("1",userName);

        for (BlogEntity blogEntity : blogEntities) {

            for (BlogCollectionVO blogCollectionVO : blogCollectionVOS) {
                if (blogEntity.getBlogId().equals(blogCollectionVO.getBlogCollectionAndUser())){
                    blogEntity.setBlogCode(blogCollectionVO.getBlogCollectionCode());
                    blogEntity.setBlogCodeText(blogCollectionVO.getBlogCollectionCodeText());
                }
            }
        }

        Example exampleCount = new Example(BlogEntity.class);
        exampleCount.createCriteria().andEqualTo("blogShare","1");

        int count = blogMapper.selectCountByExample(exampleCount);

        BlogVO pageBlog = new BlogVO();
        pageBlog.setBlogEntities(blogEntities);
        pageBlog.setTotal(String.valueOf(count));

        return pageBlog;
    }

    @Override
    public BlogEntity shareIdBlog(String id) {
        Example example = new Example(BlogEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogShare","1");
        criteria.andEqualTo("blogId",id);
        BlogEntity blogEntity = blogMapper.selectOneByExample(example);

        return  blogEntity;
    }

    @Override
    public Boolean blogCollection(String code,String userName,String id) {

        BlogCollectionEntity blogCollectionVO = new BlogCollectionEntity();
        blogCollectionVO.setBlogCollectionAndUser(id);
        if ("1".equals(code)){
            blogCollectionVO.setBlogCollectionCode("1");
            blogCollectionVO.setBlogCollectionCodeText("已收藏");
        }else {
            blogCollectionVO.setBlogCollectionCode("0");
            blogCollectionVO.setBlogCollectionCodeText("未收藏");
        }
        blogCollectionVO.setBlogCollectionUser(userName);


        Example example = new Example(BlogCollectionEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogCollectionAndUser",id);
        criteria.andEqualTo("blogCollectionUser",userName);
        BlogCollectionEntity blogCollectionEntity = blogCollectionMapper.selectOneByExample(example);
//        int count = blogCollectionMapper.selectCountByExample(example);

        if (ObjectUtil.isEmpty(blogCollectionEntity)){
            blogCollectionVO.setBlogCollectionId(UUIDUtil.getUuid32());
            blogCollectionMapper.insertSelective(blogCollectionVO);
        }else {
            blogCollectionVO.setBlogCollectionId(blogCollectionEntity.getBlogCollectionId());
            int i = blogCollectionMapper.updateByPrimaryKeySelective(blogCollectionVO);
            if (i<=0){
                return false;
            }
        }

        return true;
    }

    @Override
    public BlogVO findAllBlogCollection(String userName, int pageSize) {

        List<BlogEntity> blogDaoEntity = new ArrayList<>();
        //展示已经分享的内容
        List<BlogEntity> blogEntities = blogMapper.blogCollectionShare("1");

        //用户收藏的全部数据
        List<BlogCollectionVO> blogCollectionVOS = blogMapper.blogPageCollection("1",(pageSize-1)*5,pageSize*5,userName);

        for (BlogEntity blogEntity : blogEntities) {

            for (BlogCollectionVO blogCollectionVO : blogCollectionVOS) {
                if (blogEntity.getBlogId().equals(blogCollectionVO.getBlogCollectionAndUser())){
                    blogEntity.setBlogCode(blogCollectionVO.getBlogCollectionCode());
                    blogEntity.setBlogCodeText(blogCollectionVO.getBlogCollectionCodeText());
                    blogDaoEntity.add(blogEntity);
                }
            }
        }

        Example exampleCount = new Example(BlogCollectionEntity.class);
        Example.Criteria criteria = exampleCount.createCriteria();
        criteria.andEqualTo("blogCollectionUser",userName);
        criteria.andEqualTo("blogCollectionCode","1");
        int count = blogCollectionMapper.selectCountByExample(exampleCount);


//        System.out.println("===="+count);
        BlogVO pageBlog = new BlogVO();
        pageBlog.setBlogEntities(blogDaoEntity);
        pageBlog.setTotal(String.valueOf(count));

        return pageBlog;
    }

}
