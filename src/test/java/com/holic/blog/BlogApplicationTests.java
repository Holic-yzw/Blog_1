package com.holic.blog;

import com.holic.blog.entity.Type;
import com.holic.blog.service.BlogService;
import com.holic.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    BlogService service;

    @Test
    void contextLoads() {
        System.out.println(
                service.countExistBlog("测试标题1")
        );
    }

}
