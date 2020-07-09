package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.CommonUser;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.SearchBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForAdmin;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
public interface BlogService {

    PageInfo<ShowBlogForAdmin> listBlog(Integer pageNum, Integer pageSize, SearchBlogForAdmin blog);

    PageInfo<ShowBlogForAdmin> listBlog(Integer pageNum, Integer pageSize);

    Blog getBlog(Long id);

    Blog getBlogForView(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    int countExistBlog(String title);

    String getTagIdByBlogId(Long id);

    CommonUser getAuthorByBlogId(Long id);

    int updateViewTimes(Long id);

}
