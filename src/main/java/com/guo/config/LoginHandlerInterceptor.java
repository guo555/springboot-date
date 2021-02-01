package com.guo.config;

import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功之后有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        Object loginadmin = request.getSession().getAttribute("loginadmin");

        if(loginUser == null && loginadmin==null){//没有登录
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }else {
            return true;
        }

    }


}
