package com.game.netgame.service;

import com.game.netgame.entity.User;
import com.game.netgame.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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

    @Test
    public void updatePassword(){
        userService.changePassword(24,"you","1234567","1234566");
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setUid(27);
        user.setPhone("1130");
        user.setEmail("110@10.com");
        user.setGender(1);
        user.setModifiedUser("admin356");
        user.setModifiedTime(new Date());
        userService.changeInfo(27,"930",user);
    }

    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(27));
    }
}
