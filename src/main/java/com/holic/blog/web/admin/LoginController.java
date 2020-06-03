package com.holic.blog.web.admin;

import com.holic.blog.entity.Admin;
import com.holic.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminService service;

    @GetMapping
    public String tologinPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String toIndexPage(@RequestParam String userName,
                          @RequestParam String passWord,
                          HttpSession session,
                          RedirectAttributes attributes){
        Admin admin = service.checkAdmin(userName, passWord);

        if (admin != null) {
            admin.setPassWord(null);
            session.setAttribute("admin", admin); // 登录信息回传
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码有误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/admin";
    }
}
