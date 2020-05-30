package com.holic.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) // @ExceptionHandler 表示此方法用来处理异常，级别是Exception及其子类
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        // 记录异常信息
        logger.error("RequestUrl: {}, Exception: {}", request.getRequestURL(), e); // {}表示占位符，会将后边的信息传进去

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error"); // 将以上信息展示到哪个页面

        return mv;
    }
}
