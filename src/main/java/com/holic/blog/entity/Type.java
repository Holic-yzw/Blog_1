package com.holic.blog.entity;

import java.util.ArrayList;
import java.util.List;

public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public Type() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
