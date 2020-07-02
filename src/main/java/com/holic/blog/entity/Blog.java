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

    private Long blogTypeId;
    private Long[] blogTagId;
    private Long blogAdminId;

}
