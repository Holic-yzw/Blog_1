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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

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
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, Integer pageNum) {

        pageNum = pageNum == null ? 1 : pageNum;
        Integer pageSize = 1;

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

        PageInfo<ExampleForShowBlog> newPage = blogService.listBlog(pageNum, 1, blog);
        System.out.println(newPage.getPages());
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
    public String add(@Valid Blog blog, BindingResult result, RedirectAttributes attributes) {

        int count = blogService.countExistBlog(blog.getTitle());
        if (count > 0) {
            result.rejectValue("title", "titleRepeat", "博客名称不能重复！");
        }

        if (result.hasErrors()) {
            return "admin/blogs-input";
        }

        logger.info("\n 新增日志入参：{} \n", blog);

        int i = blogService.saveBlog(blog);
        if (i == 0) {
            attributes.addFlashAttribute("failMessage","新增博客失败！");
        } else {
            attributes.addFlashAttribute("succMessage","新增博客成功！");
        }

        return "redirect:/admin/blogs";
    }
}
