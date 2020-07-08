package com.holic.blog.service.implement;

import com.holic.blog.entity.Comment;
import com.holic.blog.mapper.CommentMapper;
import com.holic.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Title: CommentServiceImpl
 * @author: HOLiC
 * @date: 2020/7/8
 */
@Service
public class CommentServiceImpl implements CommentService {

    private  String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        List<Comment> list = commentMapper.findCommentByBlogId(id);
        ArrayList arrayList = new ArrayList(sortComment(list));
        return arrayList;
    }


    private Set<Comment> sortComment(List<Comment> comments){

        ArrayList<Comment> rootComment = new ArrayList<>();
        ArrayList<Comment> childComment = new ArrayList<>();
        // 将父子评论分开
        for (Comment comment:comments
             ) {
            if (comment.getParentCommentId() == -1) {
                rootComment.add(comment);
            } else {
                childComment.add(comment);
            }
        }

        TreeSet<Comment> root = new TreeSet<>(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                Comment c1 = o1;
                Comment c2 = o2;
                return c2.getCreateDate().compareTo(c1.getCreateDate());
            }
        });

        // 双重循环，将子评论归结到父评论下
        for (Comment comment:rootComment
             ) {
            // 存放子评论的集合
            TreeSet<Comment> child = new TreeSet<>(new Comparator<Comment>() {
                @Override
                public int compare(Comment o1, Comment o2) {
                    Comment c1 = o1;
                    Comment c2 = o2;
                    return c2.getCreateDate().compareTo(c1.getCreateDate());
                }
            });

            Long parentId = comment.getId();
            for (Comment comment1: childComment
                 ) {
                if (comment1.getParentCommentId() == parentId) {
                    child.add(comment1);
                }
            }

            comment.setChildComment(child);
            comment.setCount(child.size());

            root.add(comment);
        }
        return root;
    }


    @Transactional
    @Override
    public int saveComment(Comment comment) {
        System.out.println(date);
        comment.setCreateDate(date);
        comment.setAvatar("/images/avatar/joe.jpg"); // 这个后期优化，从前端获取
        int i = commentMapper.saveComment(comment);

        if (i ==0) {
            throw new RuntimeException("保存评论失败");
        }
        return 1;
    }
}
