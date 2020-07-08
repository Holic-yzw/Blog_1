package com.holic.blog.mapper;

import com.holic.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: CommentMapper
 * @author: HOLiC
 * @date: 2020/7/8
 */
@Repository
public interface CommentMapper {

    // 这个查询逻辑待修改
    List<Comment> findCommentByBlogId(Long id);

    int saveComment(Comment comment);
}
