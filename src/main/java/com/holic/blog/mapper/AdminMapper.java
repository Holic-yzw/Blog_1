package com.holic.blog.mapper;

import com.holic.blog.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    Admin checkAdminByUserNameAndPassWord(String userName, String passWord);
}
