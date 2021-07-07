package com.game.netgame.service.impl;

import com.game.netgame.entity.User;
import com.game.netgame.mapper.UserMapper;
import com.game.netgame.service.IUserService;
import com.game.netgame.service.ex.UsernameDuplicateException;
import com.game.netgame.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    // 声明Mapper对象
    @Autowired
    private UserMapper userMappper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        System.out.println(username+" is rigging.");
        // 判断用户名是否冲突/重复
        if (userMappper.findByUsername(username) != null) {
            throw new UsernameDuplicateException("用户名重复");
        }
        // 判断对象的数据是否为空

        if (user.getPassword() == null) {
            throw new UsernameDuplicateException("密码不能为空");
        }
        // 执行数据插入操作
        // set new time and idDelete
        Date date = new Date();
        user.setCreatedTime(date);
        user.setIsDelete(0);
        // deal with password and salt
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMd5Password(user.getPassword(), user.getSalt()));
        // insert into database
        int rows = userMappper.insertUser(user);
        // insert error
        if (rows != 1) {
            throw new InsertException("插入异常");
        }
    }

    /**
     * @param password
     * @param salt
     * @return md5
     */
    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5Digest((password + salt).getBytes(StandardCharsets.UTF_8)).toString().toUpperCase();
        }
        return password;
    }
}
