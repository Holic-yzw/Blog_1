package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;

import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5
 */
public interface IndexService {

    PageInfo<ShowBlogForViewer> listAllBlog(Integer pageNum, Integer pageSize);

    PageInfo<ShowBlogForViewer> listQueryBlog(Integer pageNum, Integer pageSize, String query);

    List<ShowTypeForViewer> listTypes();

    List<ShowTagForViewer> listTags();

    List<ShowLatestBlogForViewer> listLatestBlog();

}
