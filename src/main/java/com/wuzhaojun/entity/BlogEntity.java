package com.wuzhaojun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:30
 */
@Data
@Table(name="blog")
public class BlogEntity {

    @Id
    @Column(name = "blog_id")
    private String blogId;

    @Column(name = "blog_content")
    private String blogContent;

    @Column(name = "blog_title")
    private String blogTitle;

    @Column(name = "blog_code")
    private String blogCode;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "blog_release_time")
    private Date blogReleaseTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "blog_update_time")
    private Date blogUpdateTime;

    @Column(name = "blog_share")
    private String blogShare;

    @Column(name = "blog_share_text")
    private String blogShareText;

    //博客的用户名
    @Column(name = "blog_user")
    private String blogUser;

    @Column(name = "blog_author")
    private String blogAuthor;

}
