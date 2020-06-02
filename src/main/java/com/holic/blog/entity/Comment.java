package com.holic.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {

    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private Date createDate;

    private Blog blog;

    private Comment parentComment;

    private List<Comment> replyComments = new ArrayList<>();

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Blog getBlog() {
        return blog;
    }

    public Comment() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
