package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: ShowBlogForAdmin
 * @Description: 后台日志查询返回对象
 * @author: HOLiC
 * @date: 2020/7/1
 */
@Data
@NoArgsConstructor
public class ShowBlogForAdmin {
    private Long id;
    private String title;
    private String typeName;
    private String recommend;
    private String published; // 为on时是发布，否则为草稿
    private String updateDate;
}
