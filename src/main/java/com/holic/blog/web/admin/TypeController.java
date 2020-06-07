package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Type;
import com.holic.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Title：TypeController
 * @author：Administrator
 * @date：2020/6/7
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model, Integer pageNum) {

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<Type> newPage = typeService.listType(pageNum, 3); //分页大小写死
        int pages = newPage.getPages();
        model.addAttribute("page", newPage);
        return "admin/types";
    }

    @GetMapping("/types/add")
    public String add(){
        return "admin/types-input";
    }

    @PostMapping("/types/addType")
    public String addType(Type type, RedirectAttributes attributes) {

        int i = typeService.saveType(type);
        System.out.print(i);
        if (i == 0) {
            attributes.addAttribute("message","添加分类失败！");
        } else {
            attributes.addAttribute("message","添加分类成功！");
        }
        return "redirect:/admin/types";
    }
}
