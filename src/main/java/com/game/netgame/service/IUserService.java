package com.game.netgame.service;

import com.game.netgame.entity.User;

public interface IUserService {
    public void reg(User user);

    public User login(String username, String password);
}
