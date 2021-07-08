package com.game.netgame.controller;

import com.game.netgame.entity.User;
import com.game.netgame.service.IUserService;
import com.game.netgame.service.ex.InsertException;
import com.game.netgame.service.ex.UsernameDuplicateException;
import com.game.netgame.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> index(User user) {
        System.out.println("111111");
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password) {
        User u = userService.login(username, password);
        return new JsonResult<>(OK, u);
    }
}
