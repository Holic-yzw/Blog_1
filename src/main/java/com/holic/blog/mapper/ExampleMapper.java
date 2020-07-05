package com.holic.blog.mapper;

import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5  12:56
 */
@Repository
public interface ExampleMapper {

    List<ShowBlogForViewer> findAllBlog();

    //关于模糊查询：
    //1. like '%${param}%'
    //2. like concat('%',#{param},'%')
    //第一种会引发SQL注入的问题，所以采用第二种，用mysql的函数来解决字符串的拼接问题
    List<ShowBlogForViewer> findBlogByQuery(String query);

    // 按分类下博客数量降序，取前6个
    List<ShowTypeForViewer> fianTypeOfMostBlog();
    // 按标签下博客数量降序，取前6个
    List<ShowTagForViewer> fianTagOfMostBlog();

    List<ShowLatestBlogForViewer> findLatestBlog();

}
