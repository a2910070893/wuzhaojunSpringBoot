package com.wuzhaojun.service;

import com.wuzhaojun.entity.UserEntity;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/3/19 11:21
 */
public interface UserService {
    public boolean insertUser(UserEntity userEntity);

    public List<UserEntity> findUser();

    public boolean updateUser(UserEntity userEntity);
}
