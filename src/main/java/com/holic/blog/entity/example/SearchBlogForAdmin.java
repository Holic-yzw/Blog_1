package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: SearchBlogForAdmin
 * @Description: 后台条件查询日志的入参
 * @author: Administrator
 * @date: 2020/7/1
 */
@Data
@NoArgsConstructor
public class SearchBlogForAdmin {
    private String title;
    private Long typeId;
    private String recommend;
    // 页码
    private Integer page;
}
