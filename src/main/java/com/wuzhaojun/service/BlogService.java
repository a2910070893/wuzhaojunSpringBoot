package com.wuzhaojun.service;

import com.wuzhaojun.entity.BlogEntity;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:37
 */
public interface BlogService {
    List<BlogEntity> findAllBlog();

    Boolean insertBlog(BlogEntity blogEntity);

    Boolean updateBlog(BlogEntity blogEntity);

    BlogEntity findIdBlog(String id);

    Boolean deleteBlog(String id);

    Boolean shareBlog(String id,String code);

    List<BlogEntity> shareAllBlog();

    BlogEntity shareIdBlog(String id);
}
