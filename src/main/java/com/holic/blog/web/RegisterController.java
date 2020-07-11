package com.holic.blog.web;

import com.holic.blog.entity.CommonUser;
import com.holic.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: RegisterController
 * @author: HOLiC
 * @date: 2020/7/10
 */
@Controller
@RequestMapping("/holic")
public class RegisterController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/register")
    public String toRegisterPage(){
        return "register";
    }

    @ResponseBody
    @PostMapping("/register")
    public String registerViewer(CommonUser user) {
        int i = adminService.registerNewViewer(user);
        String result = "";
        if (i == 1) {
            result = "succ";
        } else {
            result = "fail";
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/registerCheck")
    public Integer checkItem(@RequestParam String username, @RequestParam String nickname, @RequestParam String email) {

        int data = adminService.checkRegisterData(username, nickname, email);

        return data;
    }
}
