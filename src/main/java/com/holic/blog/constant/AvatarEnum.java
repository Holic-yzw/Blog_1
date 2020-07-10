package com.holic.blog.constant;

import lombok.Getter;

/* *
 * @Description: 头像路径枚举
 * @author: HOLiC
 * @date: 2020/7/10
 **/
@Getter
public enum AvatarEnum {
    Christian(1, "/images/avatar/christian.jpg"),
    Elliot(2, "/images/avatar/elliot.jpg"),
    Jenny(3, "/images/avatar/jenny.jpg"),
    Joe(4, "/images/avatar/joe.jpg"),
    Matt(5, "/images/avatar/matt.jpg"),
    Steve(6, "/images/avatar/steve.jpg"),
    Stevie(7, "/images/avatar/steie.jpg");

    private Integer code;
    private String uri;

    AvatarEnum(Integer code, String uri) {
        this.code = code;
        this.uri = uri;
    }
}
