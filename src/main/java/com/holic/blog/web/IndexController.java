package com.holic.blog.web;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/holic")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Integer pageSize = 2;

    @Autowired
    private IndexService indexService;

    @GetMapping("/index")
    public String index(Model model, Integer pageNum){

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<ShowBlogForViewer> blog = indexService.listAllBlog(pageNum, pageSize);
        model.addAttribute("page", blog);
        model.addAttribute("tags", indexService.listTags());
        model.addAttribute("types", indexService.listTypes());
        model.addAttribute("reBlog", indexService.listLatestBlog());

        return "index";
    }

    @RequestMapping("/search")
    public String search(String query, Integer pageNum, Model model) {

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<ShowBlogForViewer> blog = indexService.listQueryBlog(pageNum, pageSize, query);
        model.addAttribute("page", blog);
        model.addAttribute("query", query);

        return "search";
    }
}
