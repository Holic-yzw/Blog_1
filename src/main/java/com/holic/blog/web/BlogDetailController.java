package com.holic.blog.web;

import com.holic.blog.entity.Blog;
import com.holic.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：HOLiC
 * @date：2020/7/5  17:32
 */
@Controller
@RequestMapping("/holic")
public class BlogDetailController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{id}")
    public String viewBlog(@PathVariable Long id, Model model){

        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);

        return "blogdetail";
    }
}
