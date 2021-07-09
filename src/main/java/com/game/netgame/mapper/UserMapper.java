package com.game.netgame.mapper;

import com.game.netgame.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;

//@Mapper
public interface UserMapper {
    /**
     * @User 用户
     * 插入用户
     */
    int insertUser(User user);

    /**
     * @String 用户名
     * 通过用户名获取用户信息
     * 使用注解
     */
    @ResultMap("UserEntityMap")
    @Select("select * from `t_user` where username = #{username}")
    User findByUsername(String username);


    Integer updatePasswordByUid(Integer uid,
                                String password,
                                @Param("ModifiedUser") String mu,
                                @Param("ModifiedTime") Date mt);

    @ResultMap("UserEntityMap")
    @Select("select * from t_user where uid = #{uid}")
    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);
}
