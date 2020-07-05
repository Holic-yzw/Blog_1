package com.holic.blog.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String picUrl;
    private String source;
    private Integer viewTimes;
    private String appreciation;
    private String copyright;
    private String recommend;
    private String published;
    private String comment;
    private String createDate;
    private String updateDate;

    private String description;

    private Long blogTypeId;
    private String blogTagId; //存成数组在编辑功能页面回显时返到页面的值是无法解析的，必须存成字符串 "1,2,3" 这样
    private Long blogAdminId;

}
