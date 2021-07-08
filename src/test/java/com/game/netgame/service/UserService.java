package com.game.netgame.service;

import com.game.netgame.entity.User;
import com.game.netgame.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserService {
    @Autowired
    private IUserService userService;

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Test
    public void reg() {
        User user = new User();
        user.setUsername("e1");
        user.setPassword("123456");
        userService.reg(user);
    }

    @Test
    public void login() {
        userService.login("abc", "123456");
    }
}
