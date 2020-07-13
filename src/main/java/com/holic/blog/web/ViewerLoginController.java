package com.holic.blog.web;

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

/**
 * @author：HOLiC
 * @date：2020/7/10  22:04
 */
@Controller
@RequestMapping("/holic")
public class ViewerLoginController {

    @Autowired
    private AdminService service;

    @GetMapping("/login")
    public String toViewerLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password,
                                        HttpSession session,RedirectAttributes attributes) {
        CommonUser viewer = service.checkAdmin(username, password);

        if (viewer != null) {
            viewer.setPassWord(null);
            session.setAttribute("viewer", viewer); // 登录信息回传
            return "redirect:/holic/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码有误");
            return "redirect:/holic/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("viewer");
        return "redirect:/holic/index";
    }

}
