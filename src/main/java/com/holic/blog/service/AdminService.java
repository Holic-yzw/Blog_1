package com.holic.blog.service;

import com.holic.blog.entity.Admin;

public interface AdminService {

    Admin checkAdmin(String userName, String passWord);
}
