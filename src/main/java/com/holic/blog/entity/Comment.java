package com.holic.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Comment {

    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private String createDate;

    private Long blogId;
    // 自关联
    private Long parentCommentId;
    // 被回复者昵称，冗余字段避免关联查询
    private String parentNickName;
    // 统计子评论个数
    private Integer count = 0;
    //子评论
    private List<Comment> childComment;

}
