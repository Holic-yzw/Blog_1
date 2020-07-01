package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.ExampleForSearchBlog;
import com.holic.blog.entity.example.ExampleForShowBlog;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
public interface BlogService {

    PageInfo<ExampleForShowBlog> listBlog(Integer pageNum, Integer pageSize, ExampleForSearchBlog blog);

    PageInfo<ExampleForShowBlog> listBlog(Integer pageNum, Integer pageSize);

    Blog getBlog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
