package com.wuzhaojun.converter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        System.out.println("-----------------"+loginUser);
        if(loginUser==null) {
            System.out.println("=================");
            session.setAttribute("msg","没有权限");
//            String userName = null;
//            String password = null;
//            String url = request.getContextPath()+"/api/login/login/"+userName+"/"+password;
//            response.sendRedirect(url);//拦截后跳转的方法
//            System.out.println("已成功拦截并转发跳转"+url);
            return false;
        }

        return true;

    }


}
