package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：前端首页博客展示对象
 * @author：HOLiC
 * @date：2020/7/5
 */
@Data
@NoArgsConstructor
public class ShowBlogForViewer {
    private Long id;
    private String title;
    private String description;
    private String adminNickName;
    private String adminAvatar;
    private String updateDate;
    private Integer viewTimes;
    private String picUrl;
    private String typeName;
}
