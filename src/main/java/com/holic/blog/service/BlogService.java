package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.CommonUser;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.SearchBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;

import java.util.List;
import java.util.Map;

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

    Map<String, List<ShowBlogForViewer>> archiveBlog();

    List<ShowLatestBlogForViewer> listLatestBlog();

}
