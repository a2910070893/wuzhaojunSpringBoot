package com.wuzhaojun.service;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.vo.BlogVO;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:37
 */
public interface BlogService {
    BlogVO findAllBlog(String userName,int pageSize);

    Boolean insertBlog(BlogEntity blogEntity);

    Boolean updateBlog(BlogEntity blogEntity);

    BlogEntity findIdBlog(String id);

    Boolean deleteBlog(String id);

    Boolean shareBlog(String id,String code);

    BlogVO shareAllBlog(int pageSize, int size,String userName);

    BlogEntity shareIdBlog(String id);

    Boolean blogCollection(String code,String userName,String id);

    BlogVO findAllBlogCollection(String userName,int pageSize);
}
