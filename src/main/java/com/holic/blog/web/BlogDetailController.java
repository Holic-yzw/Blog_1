package com.holic.blog.web;

import com.holic.blog.entity.Admin;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.Comment;
import com.holic.blog.entity.Tag;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.CommentService;
import com.holic.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5  17:32
 */
@Controller
@RequestMapping("/holic")
public class BlogDetailController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/blog/{id}")
    public String viewBlog(@PathVariable Long id, Model model){

        Blog blog = blogService.getBlogForView(id);
        Admin author = blogService.getAuthorByBlogId(id);
        List<Tag> tags = tagService.getTagByBlogId(id);

        model.addAttribute("blog", blog);
        model.addAttribute("author", author);
        model.addAttribute("tags", tags);

        return "blogdetail";
    }

    @GetMapping("/comment/{blogId}")
    public String loadComment(@PathVariable Long blogId, Model model) {

        List<Comment> comments = commentService.listCommentByBlogId(blogId);

        if (comments.size() == 0) {
            ArrayList<Comment> comments1 = new ArrayList<>();
            model.addAttribute("comments", comments1);
            return "blogdetail :: commentList";
        }

        model.addAttribute("comments", comments);
        return "blogdetail :: commentList";
    }

    @PostMapping("/comments")
    public String saveComment(Comment comment) {

        logger.info("\n 保存评论入参：{} \n", comment);

        Long blogId = comment.getBlogId();

        commentService.saveComment(comment);
        return "redirect:/holic/comment/"+blogId;
    }
}
