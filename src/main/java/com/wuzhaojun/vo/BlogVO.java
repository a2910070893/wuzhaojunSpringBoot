package com.wuzhaojun.vo;

import com.wuzhaojun.entity.BlogEntity;
import lombok.Data;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/5/7 16:07
 */
@Data
public class BlogVO {
    private List<BlogEntity> blogEntities;

    private String total;
}
