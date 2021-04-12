package com.wuzhaojun.service.impl;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.mapper.BlogMapper;
import com.wuzhaojun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.power.common.util.UUIDUtil;

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
    public List<BlogEntity> findAllBlog() {
        List<BlogEntity> blogEntities = blogMapper.selectAll();
        return blogEntities;
    }

    @Override
    public Boolean insertBlog(BlogEntity blogEntity) {


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        blogEntity.setBlogId(UUIDUtil.getUuid32());
        blogEntity.setBlogReleaseTime(java.sql.Date.valueOf(sdf.format(date)));
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

}
