package com.game.netgame.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 定义一个处理器拦截器 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截方法：
     * @param request 请求对象并且保存有客户端提交过来的所有数据(SessionId)
     * @param response 响应对象
     * @param handler 处理器(SpringMVC核心组件，将url地址位置和一个Controller绑定在一起)
     * @return true:则不仅拦截（放行）；false:则进行拦截（请求方法不能执行）
     * @throws Exception 如果产生异常进行抛出
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.首先想法获取到Session对象(application、Context、request)
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("uid");
        // 2.检查这个对象中是否有uid值
        if (obj == null) { // 如果没有获取到值则拦截
            // 2.1 重新(重定向)进入到login.html页面
            // sendRedirect方法表示重定向，需要传递一个url地址
            response.sendRedirect("/web/login.html");
            return false;
        }
        // 如果上面if语句没有执行则表示获取到uid，则放行
        return true;
    }
}
