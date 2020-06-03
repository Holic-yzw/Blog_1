package com.holic.blog.service.implement;

import com.holic.blog.entity.Admin;
import com.holic.blog.mapper.AdminMapper;
import com.holic.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public Admin checkAdmin(String userName, String passWord) {

        Admin admin = mapper.checkAdminByUserNameAndPassWord(userName, passWord);

        return admin;
    }
}
