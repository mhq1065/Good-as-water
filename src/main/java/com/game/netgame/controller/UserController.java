package com.game.netgame.controller;

import com.game.netgame.entity.User;
import com.game.netgame.service.IUserService;
import com.game.netgame.service.ex.InsertException;
import com.game.netgame.service.ex.UserNotFoundException;
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
        session.setAttribute("uid", u.getUid());
        session.setAttribute("username", u.getUsername());
        session.setAttribute("avatar", u.getAvatar());
        return new JsonResult<>(OK, u);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer userid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        System.out.println((Integer) userid + " is changing pwd");
        userService.changePassword(userid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer userid = getUidFromSession(session);
        if (userService.getByUid(userid) == null) {
            System.out.println("用户不存在");
            throw new UserNotFoundException("用户不存在");
        }
        String username = getUsernameFromSession(session);
        System.out.println(String.format("%d:%s is changing info.",userid, username));
        System.out.println(user);
        userService.changeInfo(userid, username, user);
        return new JsonResult<Void>(Modified);
    }

    @RequestMapping("user_info")
    public JsonResult<User> getUserInfo(HttpSession session){
        Integer userid = getUidFromSession(session);
        User user = userService.getByUid(userid);
        if (user == null) {
            System.out.println("用户不存在");
            throw new UserNotFoundException("用户不存在");
        }
        return new JsonResult<>(OK, user);
    }
}
