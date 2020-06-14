package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.Tag;
import com.holic.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title：BlogController
 * @Description：博客管理页
 * @author：Administrator
 * @date：2020/6/4
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public String blogs(Model model, Integer pageNum, Blog blog) {
        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<Blog> newPage = blogService.listBlog(pageNum, 10, blog);
        model.addAttribute("page", newPage);
        return "admin/blogs";
    }

    @GetMapping("/blogs/add")
    public String add() {
        return "admin/blogs-input";
    }
}
