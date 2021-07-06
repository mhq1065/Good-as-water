package com.game.netgame.mapper;

import com.game.netgame.entity.User;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface UserMapper {
    /**
     * @User user
     * 插入用户
     */
    public void insertUser(User user);

    /**
     * @String username
     * 通过用户名获取用户信息
     * 使用注解
     */
    @ResultMap("UserEntityMap")
    @Select("select * from `t_user` where username = #{username}")
    public User findByUsername(String username);
}
