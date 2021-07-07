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
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> index(User user) {
        JsonResult<Void> result=new JsonResult<Void>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("注册成功");
        } catch (UsernameDuplicateException e) {
            result.setState(402);
            result.setMessage("用户名被占用");
            e.printStackTrace();
        }catch (InsertException e){
            result.setState(403);
            result.setMessage("插入数据异常");
            e.printStackTrace();
        }
        return result;
    }

}
