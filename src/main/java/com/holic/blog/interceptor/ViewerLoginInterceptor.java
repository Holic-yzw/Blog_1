package com.holic.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: ViewerLoginInterceptor
 * @author: HOLiC
 * @date: 2020/7/13
 */
public class ViewerLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("viewer") == null) {
            response.sendRedirect("/holic/index");
            return false;
        }
        return true;
    }

}
