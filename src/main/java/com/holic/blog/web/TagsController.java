package com.holic.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description：TODO
 * @author：HOLiC_y
 * @date：2020/7/5 0005
 */
@Controller
@RequestMapping("/holic")
public class TagsController {

    @GetMapping("/tags")
    public String tag () {
        return "tags";
    }
}
