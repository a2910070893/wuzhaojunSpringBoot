package com.wuzhaojun.service.impl;

import com.wuzhaojun.entity.UserEntity;
import com.wuzhaojun.mapper.UserMapper;
import com.wuzhaojun.service.UserService;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.lang.model.type.ArrayType;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/3/19 11:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public boolean insertUser(UserEntity userEntity) {

        int insert = userMapper.insert(userEntity);
        if (insert>1){
            return  true;
        }
        return false;
    }

    @Override
    public List<UserEntity> findUser() {
       // List<UserEntity> userEntities = userMapper.selectAll();

        List<UserEntity> userEntities = userMapper.testFind();
        System.out.println("测试");
        return userEntities;
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        int update = userMapper.updateByPrimaryKeySelective(userEntity);
        if (update>1){
            return  true;
        }
        return false;
    }

    /**
     * 登录
     * @param userEntity
     * @return
     */
    @Override
    public Boolean login(UserEntity userEntity, HttpSession session) {

        List<UserEntity> userEntities = userMapper.selectAll();
        for (UserEntity entity : userEntities) {
            if (userEntity.getUserName().equals(entity.getUserName()) && userEntity.getPassword().equals(entity.getPassword())){
                session.setAttribute("loginUser" ,entity.getUserName());

                System.out.println(session.getAttribute("loginUser"));
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean signOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return false;
    }
}
