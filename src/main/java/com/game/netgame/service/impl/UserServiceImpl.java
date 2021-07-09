package com.game.netgame.service.impl;

import com.game.netgame.entity.User;
import com.game.netgame.mapper.UserMapper;
import com.game.netgame.service.IUserService;
import com.game.netgame.service.ex.*;
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
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        // 判断用户是否存在
        User user = userMappper.findByUid(uid);
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        // 获取salt
        String salt = user.getSalt();
        // 校验旧密码
        if (!user.getPassword().equals(getMd5Password(oldPassword, salt))) {
            throw new PasswordNotMatchException("原密码错误");
        }
        // 更新密码
        Integer result = userMappper.updatePasswordByUid(uid, getMd5Password(newPassword, salt), username, new Date());
        // update 异常捕获
        if (result != 1) {
            throw new UpdateException("更新失败");
        }
    }

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        System.out.println(username + " is registering.");
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

    @Override
    public User login(String username, String password) {
        // 判断用户是否存在
        if (userMappper.findByUsername(username) == null) {
            throw new UserNotFoundException("用户不存在");
        }
        // 获取密码
        User user = userMappper.findByUsername(username);
        String salt = user.getSalt();
        System.out.println(username + " is logging in .");
        // 判断密码是否正确
        // 字符串判断需要使用equals
        if (!getMd5Password(password, salt).equals(user.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }
        user.setSalt("0");
        user.setPassword("0");
        System.out.println(username + " log in successfully");
        return user;
    }

    /**
     * @param password
     * @param salt
     * @return md5
     */
    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8)).toString().toUpperCase();
        }
        return password;
    }
}
