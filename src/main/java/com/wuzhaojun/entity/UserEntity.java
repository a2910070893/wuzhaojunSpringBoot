package com.wuzhaojun.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author EternalPain
 * @description:
 * @date 2021/3/19 11:20
 */
@Data
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;


    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    //权限
    @Column(name = "jurisdiction")
    private String jurisdiction;
}
