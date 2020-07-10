package com.holic.blog.web;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;
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
 * @date：2020/7/5
 */
@Controller
@RequestMapping("/holic")
public class TagsController {

    private Integer pageSize =1;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String tag (@PathVariable Long id, Model model, Integer pageNum) {
        pageNum = pageNum == null ? 1 : pageNum;

        List<ShowTagForViewer> tags = tagService.listAllTagForViewer();

        if (id == -1) {
            id = tags.get(0).getTagId();
        }

        PageInfo<ShowBlogForViewer> blogs = tagService.listBlogsByTypeId(pageNum, pageSize, id);

        model.addAttribute("page", blogs);
        model.addAttribute("activeId", id);
        model.addAttribute("tags", tags);
        return "tags";
    }
}
