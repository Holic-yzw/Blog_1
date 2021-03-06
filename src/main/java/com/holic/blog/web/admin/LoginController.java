package com.holic.blog.web.admin;

import com.holic.blog.entity.CommonUser;
import com.holic.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminService service;

    @GetMapping("/h")
    public String toAdminLoginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String toIndexPage(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes attributes){
        CommonUser admin = service.checkAdmin(username, password);

        if (admin != null) {
            admin.setPassWord(null);
            session.setAttribute("admin", admin); // 登录信息回传
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码有误");
            return "redirect:/admin/h";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/admin/h";
    }
}
