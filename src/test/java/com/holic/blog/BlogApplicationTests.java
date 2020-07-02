package com.holic.blog;

import com.holic.blog.entity.Type;
import com.holic.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    TypeService typeService;

    @Test
    void contextLoads() {
        Type type = new Type();
        type.setName("testName123");
        type.setId(2L);

//        int flag = typeService.saveType(type);
//        System.out.println(flag);
//
//        Type type1 = typeService.getType(2L);
//        System.out.println(type1.toString());
//        int i = typeService.deleteType(1L);
//        System.out.println(i);
//        int i = typeService.updateType(type);
//        System.out.println(i);
    }

}
