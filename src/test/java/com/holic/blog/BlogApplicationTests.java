package com.holic.blog;

import com.holic.blog.entity.Comment;
import com.holic.blog.entity.Type;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.CommentService;
import com.holic.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {
        List<Comment> comments = commentService.listCommentByBlogId(7L);
        System.out.println(comments);
    }

}
