package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Type;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;

import java.util.List;


/**
 * @Title: TypeService
 * @Description: 分类的一些处理
 * @author: Administrator
 * @date: 2020/6/5
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getTypeByName(String name);

    PageInfo<Type> listType(Integer pageNum, Integer pageSize);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> findAllType();

    List<ShowTypeForViewer> listAllTypeForViewer();

    PageInfo<ShowBlogForViewer> liatBlogsByTypeId(Integer pageNum, Integer pageSize, Long id);
}
