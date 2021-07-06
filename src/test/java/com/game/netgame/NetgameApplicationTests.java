package com.game.netgame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class NetgameApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() {
        try {
            Connection conn = dataSource.getConnection();
            System.out.print(conn);
        } catch(SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
