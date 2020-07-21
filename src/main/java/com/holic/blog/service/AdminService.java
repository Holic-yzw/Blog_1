package com.holic.blog.service;

import com.holic.blog.entity.CommonUser;

public interface AdminService {

    CommonUser checkAdmin(String userName, String passWord);

    CommonUser checkView(String userName, String passWord);

    int registerNewViewer(CommonUser user);

    int checkRegisterData(String username, String nickname, String email);
}