package com.game.netgame.service;

import com.game.netgame.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IUserService {
    void reg(User user);

    User login(String username, String password);

    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * @param uid 用户uid
     * @param username 修改者用户名
     * @param user 用户信息
     */
    void changeInfo(Integer uid, String username, User user);

    User getByUid(Integer uid);
}
