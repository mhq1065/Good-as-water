package com.game.netgame.controller;

import com.game.netgame.service.ex.*;
import com.game.netgame.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK = 200;

    protected Integer getUidFormSession(HttpSession session) {
        return Integer.valueOf(
                session.getAttribute("uid").toString());
    }

    protected final String getUsernameFormSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }


    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        System.out.println("Catch ServiceException");
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicateException) {
            result.setState(402);
            result.setMessage("用户名被占用");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(403);
            result.setMessage("密码错误");
        } else if (e instanceof UserNotFoundException) {
            result.setState(404);
            result.setMessage("用户不存在");
        } else if (e instanceof InsertException) {
            result.setState(405);
            result.setMessage("插入数据异常");
        }
        return result;
    }

}
