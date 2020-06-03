package com.holic.blog.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

    private Long id;
    private String title;
    private String content;
    private String picUrl;
    private String source;
    private Integer viewTimes;
    private Byte appreciation;
    private Byte copyright;
    private Byte recommend;
    private Byte published;
    private Byte comment;
    private Date createDate;
    private Date updateDate;

    private Type type;

    private List<Tag> tags = new ArrayList<>();

    private Admin admin;

    private List<Comment> comments = new ArrayList<>();

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", source='" + source + '\'' +
                ", viewTimes=" + viewTimes +
                ", appreciation=" + appreciation +
                ", copyright=" + copyright +
                ", recommend=" + recommend +
                ", published=" + published +
                ", comment=" + comment +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", type=" + type +
                ", tags=" + tags +
                ", admin=" + admin +
                ", comments=" + comments +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public void setAppreciation(Byte appreciation) {
        this.appreciation = appreciation;
    }

    public void setCopyright(Byte copyright) {
        this.copyright = copyright;
    }

    public void setRecommend(Byte recommend) {
        this.recommend = recommend;
    }

    public void setPublished(Byte published) {
        this.published = published;
    }

    public void setComment(Byte comment) {
        this.comment = comment;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getSource() {
        return source;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public Byte getAppreciation() {
        return appreciation;
    }

    public Byte getCopyright() {
        return copyright;
    }

    public Byte getRecommend() {
        return recommend;
    }

    public Byte getPublished() {
        return published;
    }

    public Byte getComment() {
        return comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
