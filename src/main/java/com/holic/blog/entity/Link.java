package com.holic.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: Link
 * @author: Administrator
 * @date: 2020/7/1
 */
@Data
@NoArgsConstructor
public class Link {
    private Long id;
    private Long blogId;
    private Long tagId;
}

