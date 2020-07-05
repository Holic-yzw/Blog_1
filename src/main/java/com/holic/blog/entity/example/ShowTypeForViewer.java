package com.holic.blog.entity.example;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：前端首页分类展示对象
 * @author：HOLiC
 * @date：2020/7/5
 */
@Data
@NoArgsConstructor
public class ShowTypeForViewer {
    private Long typeId;
    private String typeName;
    private Integer countBlog;
}
