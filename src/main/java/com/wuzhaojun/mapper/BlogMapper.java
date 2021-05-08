package com.wuzhaojun.mapper;

import com.wuzhaojun.entity.BlogEntity;
import com.wuzhaojun.vo.BlogCollectionVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author EternalPain
 * @description:
 * @date 2021/4/1 10:37
 */
public interface BlogMapper extends Mapper<BlogEntity> {
    List<BlogEntity> blogPage(String blogShare,int pageSize,int size);

    List<BlogEntity> blogCollectionShare(String blogShare);

    List<BlogEntity> blogUserPage(String blogUser,int pageSize);

    List<BlogEntity> findAllBlogCollection(String blogUserId,int pageSize);

    List<BlogCollectionVO> blogPageCollection(String blogShare,int pageSize,int size,String userName);

    List<BlogCollectionVO> blogCollection(String blogShare,String userName);
}
