package com.game.netgame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.game.netgame.mapper")
public class NetgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetgameApplication.class, args);
	}

}
