package com.holic.blog.web;

import com.holic.blog.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
//        int b = 9/0;
        if (true) {
            throw new ResourceNotFoundException();
        }
        return "index";
    }
}
