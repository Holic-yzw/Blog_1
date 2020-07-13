package com.holic.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title：LoginInterceptor
 * @Description：登录过滤器
 * @author：Administrator
 * @date：2020/6/4
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
