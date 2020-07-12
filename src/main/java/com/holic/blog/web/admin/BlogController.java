package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.SearchBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForAdmin;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.TagService;
import com.holic.blog.service.TypeService;
import org.apache.ibatis.annotations.Param;
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

    private final Integer pageSize = 10;

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, Integer pageNum) {

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<ShowBlogForAdmin> newPage = blogService.listBlog(pageNum, pageSize);
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("page", newPage);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Model model, SearchBlogForAdmin blog) {

        Integer pageNum = blog.getPage();
        pageNum = pageNum == null ? 1 : pageNum;

        logger.info("\n 博客条件查询入参：{} \n ", blog);
        PageInfo<ShowBlogForAdmin> newPage = blogService.listBlog(pageNum, pageSize, blog);
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


    @ResponseBody
    @PostMapping("/blogs/checkTitle")
    public String checkTitle( String title) {
        // post请求不使用对象接参的话，那么参数名前后端要保持一致，不然接不到
        int count = blogService.countExistBlog(title);
        String result = "";
        if (count != 0) {
            result = "fail";
        } else {
            result = "succ";
        }
        return result;
    }

    @PostMapping("/blogs/add")
    public String add(Blog blog, RedirectAttributes attributes) {

        logger.info("\n 新增博客入参：{} \n", blog);

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

    @ResponseBody
    @GetMapping("/blogs/delete")
    public String delete(@Param("id") Long id) {

        // get请求获取参数，可以用@Param(""),引号内是前端请求时参数的名字，比如：
        //        $.get(/*[[@{/admin/blogs/delete}]]*/"/admin/blogs/delete",
        //                {id : id, name : 'jjjjj'});
        //id这个参数的取值
        //@PathVariable是为rest风格而准备的，上面这个例子就不适用
        //当然参数太多，post请求用对象接受参数更好
        String result = "";

        int i = blogService.deleteBlog(id);
        if (i == 1) {
            result = "succ";
        } else {
            result = "fail";
        }

        return result;
    }

}
