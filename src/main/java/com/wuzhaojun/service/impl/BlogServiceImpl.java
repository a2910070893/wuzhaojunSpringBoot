package com.wuzhaojun.service.impl;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.mapper.BlogMapper;
import com.wuzhaojun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        int i = blogMapper.insertSelective(blogEntity);
        if (i<=0){
            return false;
        }
        return true;
    }

}
