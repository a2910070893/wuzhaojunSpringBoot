package com.wuzhaojun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.StringUtil;
import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.mapper.BlogMapper;
import com.wuzhaojun.service.BlogService;
import com.wuzhaojun.vo.BlogVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Override
    public List<BlogEntity> findAllBlog(String userName) {
        Example example = new Example(BlogEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogUser",userName);
        List<BlogEntity> blogEntities = blogMapper.selectByExample(example);
        return blogEntities;
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
        if (i<=0){
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
     * 博客分享展示
     */
    @Override
    public BlogVO shareAllBlog(int pageSize,int size) {
        Example example = new Example(BlogEntity.class);
        example.createCriteria().andEqualTo("blogShare","1");

        List<BlogEntity> blogEntities = blogMapper.selectByExampleAndRowBounds(example, new RowBounds(pageSize - 1, size));

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

}
