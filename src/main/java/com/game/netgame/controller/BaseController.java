package com.game.netgame.controller;

import com.game.netgame.service.ex.*;
import com.game.netgame.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        System.out.println("ServiceException");
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicateException) {
            result.setState(402);
            result.setMessage("用户名被占用");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(403);
            result.setMessage("密码错误");
        } else if (e instanceof UserNotFoundException) {
            result.setState(403);
            result.setMessage("用户不存在");
        } else if (e instanceof InsertException) {
            result.setState(403);
            result.setMessage("插入数据异常");
        }
        return result;
    }

}
