package com.holic.blog.service;

import com.holic.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Title: TypeService
 * @Description: 分类的一些处理
 * @author: Administrator
 * @date: 2020/6/5
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    int updateType(Type type);

    int deleteType(Long id);
}
