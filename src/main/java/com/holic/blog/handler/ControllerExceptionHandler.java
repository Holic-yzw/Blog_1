package com.holic.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/* *
 * @Description: 全局异常处理，后台所有的异常都会在此被拦截处理
 * @author: HOLiC
 * @date: 2020/7/13
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) // @ExceptionHandler 表示此方法用来处理异常，级别是Exception及其子类
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        // 记录异常信息
        logger.error("RequestUrl: {}, Exception: {}", request.getRequestURL(), e.getMessage());

        // 如果是自定义的异常（ResourceNotFoundException，该类的返回状态码定义为404），则不处理，正常抛出，交给框架处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // 其实处理方式很简单，就是返一个页面回去
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error"); // 将以上信息展示到哪个页面

        return mv;
    }
}
