package com.wuzhaojun.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author EternalPain
 * @description:
 * @date 2021/5/8 10:40
 */
@Data
@Table(name="blog_collection")
public class BlogCollectionEntity {

    @Id
    @Column(name = "blog_collection_id")
    private String blogCollectionId;

    @Column(name = "blog_collection_code")
    private String blogCollectionCode;

    @Column(name = "blog_collection_user")
    private String blogCollectionUser;

    @Column(name = "blog_collection_code_text")
    private String blogCollectionCodeText;

    @Column(name = "blog_collection_and_user")
    private String blogCollectionAndUser;
}
