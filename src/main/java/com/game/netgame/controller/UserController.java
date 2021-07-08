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

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> index(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User u = userService.login(username, password);
        session.setAttribute("uid",u.getUid());
        session.setAttribute("username",u.getUsername());
        session.setAttribute("avatar",u.getAvatar());
        return new JsonResult<>(OK, u);
    }
}
