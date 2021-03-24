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

    @Column(name = "password")
    private String password;

}
