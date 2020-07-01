package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.ExampleForSearchBlog;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(Model model, Integer pageNum) {
        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<Blog> newPage = blogService.listBlog(pageNum, 1);
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("page", newPage);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Model model, ExampleForSearchBlog blog) {

        Integer pageNum = blog.getPage();
        pageNum = pageNum == null ? 1 : pageNum;

        logger.info("\n 日志条件查询入参：\n {}", blog);

        PageInfo<Blog> newPage = blogService.listBlog(pageNum, 1, blog);
        System.out.println(newPage.getPages());
        model.addAttribute("page", newPage);
        // 返回一个thymeleaf的片段
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/add")
    public String add() {
        return "admin/blogs-input";
    }
}
