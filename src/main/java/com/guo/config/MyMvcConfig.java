package com.guo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/admin/admin-login.html").setViewName("admin/admin-login");
        registry.addViewController("/adlogin.html").setViewName("admin/admin-login");
        registry.addViewController("/main.html").setViewName("index");
        registry.addViewController("/admain.html").setViewName("admin/admin-index");
        registry.addViewController("/list.html").setViewName("list");
        registry.addViewController("/room.html").setViewName("room");
        registry.addViewController("/date.html").setViewName("date");
        registry.addViewController("/user/add.html").setViewName("user/add");
        registry.addViewController("/updatepwd.html").setViewName("updatepwd");
        registry.addViewController("/testajax.html").setViewName("testajax");
        registry.addViewController("/testajax2.html").setViewName("testajax2");
        registry.addViewController("/testajax3.html").setViewName("testajax3");
    }

    //添加拦截器

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/admin-index","/admain.html","/adminlogin","/index",
                        "/adlogin.html","/","/userlogin","/adroom","/adminlist",
                        "/css/**","/js/**","/fonts/**");
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/adlogin.html","/adminlogin", "/admin/admin-login.html","/login.html", "/","/userlogin", "/css/**","/js/**","/fonts/**");

    }


}
