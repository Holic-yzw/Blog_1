package com.holic.blog.service.implement;

import com.holic.blog.entity.Comment;
import com.holic.blog.mapper.CommentMapper;
import com.holic.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Title: CommentServiceImpl
 * @author: HOLiC
 * @date: 2020/7/8
 */
@Service
public class CommentServiceImpl implements CommentService {

    ArrayList<Comment> rootComments = new ArrayList<>();
    ArrayList<Comment> childComments = new ArrayList<>();

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        List<Comment> list = commentMapper.findCommentByBlogId(id);
        return apartComment(list);
    }

    private List<Comment> apartComment(List<Comment> comments){

        // 将父子评论分开
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == -1) {
                rootComments.add(comment);
            } else {
                childComments.add(comment);
            }
        }

        TreeSet<Comment> sort = new TreeSet<>(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o2.getCreateDate().compareTo(o1.getCreateDate());
            }
        });

        for (Comment root: rootComments) {
            ArrayList<Comment> param = new ArrayList<>();
            ArrayList<Comment> childs = new ArrayList<>();
            param.add(root);
            recursion(param, childs);
            //排序
            sort.addAll(childs);
            ArrayList<Comment> childComments = new ArrayList<>(sort);
            root.setChildComment(childComments);
            sort.clear();
            root.setCount(childs.size());
        }

        sort.addAll(rootComments);
        ArrayList<Comment> roots = new ArrayList<>(sort);
        return roots;
    }

    private List<Comment> findChildComments (Comment comment) {
        Long parentId = comment.getId();
        ArrayList<Comment> list = new ArrayList<>();
        for (Comment child : childComments ) {
            if (child.getParentCommentId() == parentId) {
                list.add(child);
            }
        }
        return list;
    }

    private void recursion (List<Comment> roots, List<Comment> childs) {
        for (Comment root : roots) {
            List<Comment> f = findChildComments(root);
            if (f.size() == 0) {
                continue; // 不能用break
            } else {
                childs.addAll(f);
                recursion(f,childs);
            }
        }
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        comment.setCreateDate(date);
        comment.setAvatar("/images/avatar/joe.jpg"); // 这个后期优化，从前端获取
        int i = commentMapper.saveComment(comment);

        if (i ==0) {
            throw new RuntimeException("保存评论失败");
        }
        return 1;
    }
}
