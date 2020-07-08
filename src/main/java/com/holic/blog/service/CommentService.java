package com.holic.blog.service;

import com.holic.blog.entity.Comment;

import java.util.List;

/**
 * @Title: CommentService
 * @author: HOLiC
 * @date: 2020/7/8
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long id);

    int saveComment(Comment comment);
}
