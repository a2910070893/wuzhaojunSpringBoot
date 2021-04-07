package com.wuzhaojun.controller;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:39
 */
@CrossOrigin
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/findAllBlog")
    public List<BlogEntity> findAllBlog(){
        List<BlogEntity> allBlog = blogService.findAllBlog();
        return allBlog;
    }

    @PostMapping("/insertBlog")
    public Boolean insertBlog(@RequestBody BlogEntity blogEntity){
        Boolean aBoolean = blogService.insertBlog(blogEntity);
        return aBoolean;
    }

    @PostMapping("/updateBlog")
    public Boolean updateBlog(@RequestBody BlogEntity blogEntity){
        Boolean aBoolean = blogService.updateBlog(blogEntity);
        return aBoolean;
    }

    @GetMapping("/findIdBlog/{id}")
    public BlogEntity findIdBlog(@PathVariable("id") String id){
        BlogEntity idBlog = blogService.findIdBlog(id);
        return idBlog;
    }

    @DeleteMapping("/deleteBlog/{id}")
    public Boolean deleteBlog(@PathVariable("id") String id){
        Boolean deleteBlog = blogService.deleteBlog(id);
        return deleteBlog;

    }
}
