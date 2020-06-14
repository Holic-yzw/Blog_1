package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Type;
import com.holic.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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

        PageInfo<Type> newPage = typeService.listType(pageNum, 10); //分页大小写死
        model.addAttribute("page", newPage);
        return "admin/types";
    }

    @GetMapping("/types/add")
    public String add(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types/addType")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        List<Type> typeList = typeService.getTypeByName(type.getName());
        if (typeList.size() > 0) {
            result.rejectValue("name", "nameRepeat", "不能添加已存在的分类！");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        int i = typeService.saveType(type);
        if (i == 0) {
            attributes.addAttribute("message","添加分类失败！");
        } else {
            attributes.addAttribute("message","添加分类成功！");
        }
        return "redirect:/admin/types";
    }
}
