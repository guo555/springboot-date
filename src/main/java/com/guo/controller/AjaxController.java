package com.guo.controller;

import com.guo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    //第一种方式，服务器要返回一个字符串，直接使用response
    @RequestMapping("/a1")
    public void ajax(String ajaxname, HttpServletResponse response) throws IOException {
        if("ad123".equals(ajaxname)){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }
    }

    /*//第二种方式，后端返回值给前端
    @RequestMapping("/a2")
    @ResponseBody
    public List<User> ajax2()  {
        List<User> list = new ArrayList<>();
        User user = new User(120,"郭","123");
        User user1 = new User(120,"郭1","123");
        User user2 = new User(120,"郭2","123");
        list.add(user);
        list.add(user1);
        list.add(user2);
        return list;//由于加了@ResponseBody注解，默认返回字符串
    }

    @RequestMapping("/a3")
    @ResponseBody
    public String ajax3(String name,String pwd)  {
        String msg = "";
        if(name!=null){
            if("admin".equals(name)){
                msg = "ok";
            }else {
                msg = "用户名错误";
            }
        }

        if(pwd!=null){
            if("123".equals(pwd)){
                msg = "pwdOK";
            }else {
                msg = "密码错误";
            }
        }
        return msg;
    }*/
}
