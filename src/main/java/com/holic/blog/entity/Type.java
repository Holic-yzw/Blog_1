package com.holic.blog.entity;

public class Type {

    private Long id;
    private String name;

    public Type() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
