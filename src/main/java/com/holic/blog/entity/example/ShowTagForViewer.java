package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：前端首页标签展示对象
 * @author：HOLiC
 * @date：2020/7/5
 */
@Data
@NoArgsConstructor
public class ShowTagForViewer {
    private Long tagId;
    private String tagName;
    private Integer countBlog; //此表标签下有多少篇博客
}
