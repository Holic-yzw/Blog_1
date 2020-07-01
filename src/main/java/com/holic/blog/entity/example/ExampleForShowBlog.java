package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: ExampleForShowBlog
 * @Description: 日志查询返回对象
 * @author: HOLiC
 * @date: 2020/7/1
 */
@Data
@NoArgsConstructor
public class ExampleForShowBlog {
    private Long id;
    private String title;
    private String typeName;
    private String recommend;
    private String updateDate;
}
