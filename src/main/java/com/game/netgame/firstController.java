package com.game.netgame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class firstController {
    @RequestMapping("index")
    public String index(){
        return "hello.html";
    }
}
