package com.game.netgame.service;

import com.game.netgame.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IUserService {
    public void reg(User user);

    public User login(String username, String password);

    public void changePassword(Integer uid, String username, String oldPassword, String newPassword);
}
