package com.holic.blog.web.admin;

import com.holic.blog.entity.CommonUser;
import com.holic.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Title: RegisterController
 * @author: HOLiC
 * @date: 2020/7/10
 */
@Controller
@RequestMapping("/admin")
public class RegisterController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/register")
    public String toRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String registerViewer(HttpSession session, RedirectAttributes attributes, CommonUser user) {
        adminService.registerNewViewer(user);
        return "";
    }

    @GetMapping("/")
    public String checkItem(String param) {
        return "";
    }
}
