package com.holic.blog.web;

import com.holic.blog.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/{id}/{name}", method = RequestMethod.GET)
    public String index(@PathVariable Integer id, @PathVariable String name) {
//        int b = 9/0;
//        if (true) {
//            throw new ResourceNotFoundException();
//        }
        System.out.println("-----------id= "+id+"-------name= -----"+name+"------------");
        return "index";
    }
}
