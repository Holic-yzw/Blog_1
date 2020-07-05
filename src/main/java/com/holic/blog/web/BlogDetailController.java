package com.holic.blog.web;

import com.holic.blog.entity.Admin;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.Tag;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5  17:32
 */
@Controller
@RequestMapping("/holic")
public class BlogDetailController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

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
}
