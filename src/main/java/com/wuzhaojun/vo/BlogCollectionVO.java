package com.wuzhaojun.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Date;

@Data
public class BlogCollectionVO {

    private String blogId;

    private String blogContent;

    private String blogTitle;

    private String blogCode;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date blogReleaseTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date blogUpdateTime;

    private String blogShare;

    private String blogShareText;

    //博客的用户名
    private String blogUser;

    private String blogAuthor;

    private String blogCodeText;

    private String blogUserId;

    private String blogCollectionId;

    private String blogCollectionCode;

    private String blogCollectionUser;

    private String blog_collection_code_text;
}
