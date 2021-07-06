package com.game.netgame.mapper;

import com.game.netgame.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("123456");
        user.setGender(0);
        userMapper.insertUser(user);
    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("jack");
        System.out.println(user.getUid());
    }
}
