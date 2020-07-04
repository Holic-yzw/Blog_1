package com.holic.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/holic")
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
