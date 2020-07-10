package com.holic.blog.web;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;
import com.holic.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5
 */
@Controller
@RequestMapping("/holic")
public class TypesController {

    private Integer pageSize =1;

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model, Integer pageNum) {
        pageNum = pageNum == null ? 1 : pageNum;

        List<ShowTypeForViewer> types = typeService.listAllTypeForViewer();

        if (id == -1) {
            id = types.get(0).getTypeId();
        }

        PageInfo<ShowBlogForViewer> blogs = typeService.liatBlogsByTypeId(pageNum, pageSize, id);

        model.addAttribute("page", blogs);
        model.addAttribute("activeId", id);
        model.addAttribute("types", types);
        return "types";
    }
}
