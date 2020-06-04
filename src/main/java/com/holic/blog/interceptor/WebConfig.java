package com.holic.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Title：WebConfig
 * @Description：拦截器配置
 * @author：Administrator
 * @date：2020/6/4
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 将自定义的拦截器加入
                      .addPathPatterns("/admin/**") // 要拦截的路径
                      .excludePathPatterns("/admin") // 放行的路径:1登录页面，否正无法显示该页面
                      .excludePathPatterns("/admin/login"); // 放行的路径:1登录路径，否正登录表单无法提交
    }

    /* *
     * @Description:使用WebMvcConfigurationSupport替换WebMvcConfigurerAdapter后，静态资源也会被拦截，所以添加本方法
     * 放行静态资源
     * @Author:Administrator
     * @Date:2020/6/4  23:36
     * @Param:[registry]
     * @return:void
     * */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");


        super.addResourceHandlers(registry);
    }
}
