package com.holic.blog.mapper;

import com.holic.blog.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

/*
    当mapper层的接口参数中超过一个时，需要特殊处理才可以被mybatis识别，两种方法： 

    第一种 (改mapper层的接口代码):

    List<UserAccount> getUserAccountByEmail(@Param("username")int username, @Param("email")String email);

    在参数前面加上@Param("xxx") xxx就是mapper.xml中指定的参数名

    第二种 (改mapper.xml中的代码)

 <select id = "getUserAccountByEmail" resultMap = "BaseResultMap">
               SELECT * FROM user_account WHERE ua_username = #{0} AND ua_email = #{1}
  </select>

    在参数指定的名字出替换成接口中参数的序号(从0开始)*/

    Admin checkAdminByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);
}
