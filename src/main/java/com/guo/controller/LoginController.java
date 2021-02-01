package com.guo.controller;


import com.guo.mapper.AdLoginMapper;
import com.guo.mapper.UserMapper;
import com.guo.pojo.Admin;
import com.guo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserMapper usermapper;

    @Autowired
    private AdLoginMapper adLoginMapper;


    //用户登录
    @PostMapping("/userlogin")
    public String login(@RequestParam("number") String user_num,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){


        User user = usermapper.findByUsernameAndPassword(user_num,password);

        if(user!=null){
            session.setAttribute("loginUser",user.getUsername());
            return "redirect:/main.html";
        }else {
            //登录失败
            model.addAttribute("msg","用户名或者密码错误");
            return "login";
        }

    }

    //用户修改密码
    @PostMapping("/userupdate")
    public String updatepwd(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model){
        User user = usermapper.findbyname(username);


        user.setPassword(password);
        usermapper.updateUser(user);
        return "login";

    }

    //管理员登录
    @RequestMapping("/adminlogin")
    public String adminlogin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){

        Admin admin = adLoginMapper.findByAdnameAndPassword(username,password);


        if(admin!=null){
            session.setAttribute("loginadmin",username);
            return "redirect:/admain.html";
        }else {
            //登录失败
            model.addAttribute("msg","用户名或者密码错误");
            return "admin/admin-login";
        }

    }

    //用户注销
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    //管理员注销
    @RequestMapping("/adlogout")
    public String adlogout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/admin-login.html";
    }
}
