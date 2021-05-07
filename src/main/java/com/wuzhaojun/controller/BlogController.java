package com.wuzhaojun.controller;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.service.BlogService;
import com.wuzhaojun.vo.BlogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:39
 */
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/findAllBlog/{userName}/{pageSize}")
    public BlogVO findAllBlog( @PathVariable("userName") String userName,@PathVariable("pageSize") int pageSize){
        BlogVO allBlog = blogService.findAllBlog(userName, pageSize);
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
    public BlogEntity findIdBlog(@PathVariable("id") String id, HttpSession session){
//    public Boolean findIdBlog(@PathVariable("id") String id, HttpSession session){

        BlogEntity idBlog = blogService.findIdBlog(id);
//        if (idBlog !=null){
//            return true;
//        }
//        return false;
        return idBlog;
    }

    @DeleteMapping("/deleteBlog/{id}")
    public Boolean deleteBlog(@PathVariable("id") String id){
        Boolean deleteBlog = blogService.deleteBlog(id);
        return deleteBlog;

    }

    @GetMapping("/shareBlog/{id}/{code}")
    public Boolean shareBlog(@PathVariable("id") String id,@PathVariable("code") String code){
        Boolean aBoolean = blogService.shareBlog(id,code);
        return aBoolean;
    }

    @GetMapping("/shareAllBlog/{pageSize}/{size}")
    public BlogVO shareAllBlog(@PathVariable("pageSize") int pageSize, @PathVariable("size") int size){
        BlogVO blogVO = blogService.shareAllBlog(pageSize, size);
        return blogVO;
    }

    @GetMapping("/shareIdBlog/{id}")
    public BlogEntity shareIdBlog(@PathVariable("id") String id){
        BlogEntity idBlog = blogService.shareIdBlog(id);
        return idBlog;
    }

    //收藏或取消收藏
    @GetMapping("/blogCollection/{code}/{userName}/{id}")
    public Boolean blogCollection(@PathVariable("code") String code,@PathVariable("userName") String userName,@PathVariable("id") String id){
        blogService.blogCollection(code,userName,id);
        return null;
    }

    @GetMapping("/findAllBlogCollection/{userName}/{pageSize}")
    public BlogVO findAllBlogCollection( @PathVariable("userName") String userName,@PathVariable("pageSize") int pageSize){
        BlogVO allBlog = blogService.findAllBlogCollection(userName, pageSize);
        return allBlog;
    }
}
