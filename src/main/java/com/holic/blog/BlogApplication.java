package com.holic.blog;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// SpringBoot启动类的注解上排除PageHelperAutoConfiguration.class
// 因为springboot自带的DataSourceAutoConfiguration会自动配置数据源，不排除的话会重复注册而报错
@MapperScan("com.holic.blog.mapper")
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
