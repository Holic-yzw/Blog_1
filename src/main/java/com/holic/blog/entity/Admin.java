package com.holic.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Admin {

    private Long id;
    private String nickName;
    private String userName;
    private String passWord;
    private String salt;
    private String email;
    private String avatar;
    private Integer type;
    private String createDate;
    private String updateDate;
}
