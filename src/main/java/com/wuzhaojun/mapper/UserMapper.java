package com.wuzhaojun.mapper;

import com.wuzhaojun.entity.UserEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/3/19 11:21
 */
public interface UserMapper extends Mapper<UserEntity> {
    List<UserEntity> testFind();
}
