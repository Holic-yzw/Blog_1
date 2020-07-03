package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.ExampleForSearchBlog;
import com.holic.blog.entity.example.ExampleForShowBlog;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.TagService;
import com.holic.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final Integer pageSize = 5;

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, Integer pageNum) {

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<ExampleForShowBlog> newPage = blogService.listBlog(pageNum, pageSize);
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("page", newPage);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Model model, ExampleForSearchBlog blog) {

        Integer pageNum = blog.getPage();
        pageNum = pageNum == null ? 1 : pageNum;

        logger.info("\n 日志条件查询入参：{} \n ", blog);
        PageInfo<ExampleForShowBlog> newPage = blogService.listBlog(pageNum, pageSize, blog);
        model.addAttribute("page", newPage);
        // 返回一个thymeleaf的片段
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/add")
    public String gotoAddPage(Model model) {

        // 页面数据初始化，一定要注意blog对象也必须传过去，不然后续的页面校验会出错，找不到blog对象（或者找不到title等错误）
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("tags", tagService.findAllTag());
        model.addAttribute("blog",new Blog());

        return "admin/blogs-input";
    }


    @PostMapping("/blogs/checkTitle")
    public String checkTitle(@RequestParam String title, Model model) {
        int count = blogService.countExistBlog(title);
        if (count != 0) {
            model.addAttribute("error","已存在");
        } else {
            model.addAttribute("error",null);
        }
        return "admin/blogs-input :: check";
    }

    @PostMapping("/blogs/add")
    public String add(Blog blog, RedirectAttributes attributes) {

        logger.info("\n 日志入参：{} \n", blog);

        if (blog.getId() == null) {
            int i = blogService.saveBlog(blog);
            if (i == 0) {
                attributes.addFlashAttribute("failMessage","新增博客失败！");
            } else {
                attributes.addFlashAttribute("succMessage","新增博客成功！");
            }
        } else {
            int i = blogService.updateBlog(blog);
            if (i == 0) {
                attributes.addFlashAttribute("failMessage","更新博客失败！");
            } else {
                attributes.addFlashAttribute("succMessage","更新博客成功！");
            }
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        Blog blog = blogService.getBlog(id);
        String ints = blogService.getTagIdByBlogId(id);
        blog.setBlogTagId(ints);
        blog.getBlogTagId();
        model.addAttribute("blog", blog);// 将查到的博客对象返给页面进行修改
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("tags", tagService.findAllTag());

        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int i = blogService.deleteBlog(id);
        if (i == 0) {
            attributes.addFlashAttribute("failMessage","删除博客失败！");
        } else {
            attributes.addFlashAttribute("succMessage","删除博客成功！");
        }
        return "redirect:/admin/blogs";
    }

}
