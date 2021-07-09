package com.game.netgame.mapper;

import com.game.netgame.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("123456");
        user.setGender(0);
        userMapper.insertUser(user);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("T");
        System.err.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        Date d = new Date();
        userMapper.updatePasswordByUid(23, "2333", "hack", d);
    }

    @Test
    public void findByUid() {
        User u = userMapper.findByUid(27);
        System.out.println(u);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(27);
        user.setPhone("1100");
        user.setEmail("110@110.com");
        user.setModifiedUser("admin");
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);

    }
}
