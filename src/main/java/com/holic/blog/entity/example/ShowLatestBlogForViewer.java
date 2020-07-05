package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：前端首页最新推荐展示对象
 * @author：HOLiC
 * @date：2020/7/5
 */
@Data
@NoArgsConstructor
public class ShowLatestBlogForViewer {
    private Long id;
    private String title;
}
