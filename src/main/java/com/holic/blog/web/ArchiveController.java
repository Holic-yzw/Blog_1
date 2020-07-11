package com.holic.blog.web;

import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description：TODO
 * @author：HOLiC_y
 * @date：2020/7/5 0005
 */
@Controller
@RequestMapping("/holic")
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    Date a = new Date();
    @GetMapping("/archive")
    public String archive (Model model) {
        Map<String, List<ShowBlogForViewer>> map = blogService.archiveBlog();
        model.addAttribute("map", map);

        int sum = 0;
        for (Map.Entry entry : map.entrySet() ) {
            List value = (List) entry.getValue();
            sum += value.size();
        }
        model.addAttribute("sum", sum);

        return "archive";
    }
}
