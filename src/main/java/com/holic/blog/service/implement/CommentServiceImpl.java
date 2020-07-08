package com.holic.blog.service.implement;

import com.holic.blog.entity.Comment;
import com.holic.blog.mapper.CommentMapper;
import com.holic.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Title: CommentServiceImpl
 * @author: HOLiC
 * @date: 2020/7/8
 */
@Service
public class CommentServiceImpl implements CommentService {

    private String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        List<Comment> list = commentMapper.findCommentByBlogId(id);
        return list;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateDate(date);
        comment.setAvatar("/images/avatar/joe.jpg"); // 这个后期优化，从前端获取
        int i = commentMapper.saveComment(comment);

        if (i ==0) {
            throw new RuntimeException("保存评论失败");
        }
        return 1;
    }
}
