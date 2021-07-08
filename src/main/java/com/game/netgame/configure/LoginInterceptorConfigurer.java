package com.game.netgame.configure;

import com.game.netgame.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/** 注册处理器映射器 */
@Configuration // 表示当前的类是一个配置类，会被Spring自动的调用
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    // 创建一个处理器映射器的对象
    // LoginInterceptor interceptor = new LoginInterceptor();
    // 接口类型来指向具体的实现类的形式来创建Java中的对象，面向接口编程思想
    HandlerInterceptor interceptor = new LoginInterceptor();
    // 注册这个对象，这方法的作用是将拦截器添加拦截器列表(注册)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建一个List集合用于保存白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");

        patterns.add("/user/login");
        patterns.add("/user/reg");

        // InterceptorRegistration通过这个对象来添加需要被拦截的路径
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}

