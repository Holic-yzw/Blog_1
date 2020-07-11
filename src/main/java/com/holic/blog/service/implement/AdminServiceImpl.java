package com.holic.blog.service.implement;

import com.holic.blog.constant.AvatarEnum;
import com.holic.blog.entity.CommonUser;
import com.holic.blog.mapper.AdminMapper;
import com.holic.blog.service.AdminService;
import com.holic.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public CommonUser checkAdmin(String userName, String passWord) {
        String salt = mapper.getSaltByUserName(userName);
        String word = MD5Utils.findPassWord(salt, passWord);
        CommonUser admin = mapper.checkAdminByUserNameAndPassWord(userName, word);

        return admin;
    }

    @Override
    public int registerNewViewer(CommonUser user) {
        String userName = user.getUserName();
        String passWord = user.getPassWord();

        // 处理密码
        String[] code = MD5Utils.code(userName, passWord);
        String newPasWord = code[1];
        String salt = code[0];

        // 分配头像
        Random random = new Random();
        int avatarNo = random.nextInt(8);
        String uri = AvatarEnum.getUriByNo(avatarNo);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        user.setPassWord(newPasWord);
        user.setSalt(salt);
        user.setType(0);
        user.setAvatar(uri);
        user.setCreateDate(date);
        user.setUpdateDate(date);
        int i = mapper.saveCommonUserInfo(user);

        if (i == 0) {
            throw new RuntimeException("保存用户注册信息失败！");
        }
        return 1;
    }


}
