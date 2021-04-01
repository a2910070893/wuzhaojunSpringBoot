package com.wuzhaojun.controller;

import com.wuzhaojun.entity.UserEntity;
import com.wuzhaojun.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/3/19 11:17
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping("/insertUser")
    public void insertUser(@RequestBody UserEntity userEntity){
        userService.insertUser(userEntity);
    }

    @GetMapping("/findUser")
    public List<UserEntity> findUser(){
        List<UserEntity> user = userService.findUser();
        return user;
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserEntity userEntity){
        userService.updateUser(userEntity);
    }

}
