package com.guo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.guo.pojo.User;
import com.guo.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JsonController {

    @GetMapping("/json")
    @ResponseBody
    public String json() throws JsonProcessingException {
        User user = new User(120,"郭","123","123131");

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        return str; //由于使用了@ResponseBody注解，这里会将str以json格式的字符串返回，十分方便

    }

    /*@GetMapping("/json1")
    @ResponseBody
    public String json1() throws JsonProcessingException {

        List<User> list = new ArrayList<>();
        User user = new User(120,"郭","123");
        User user1 = new User(120,"郭1","123");
        User user2 = new User(120,"郭2","123");
        list.add(user);
        list.add(user1);
        list.add(user2);
        return new ObjectMapper().writeValueAsString(list);

    }*/

    @GetMapping("/time1")
    @ResponseBody
    public String time1() throws JsonProcessingException {

        Date date = new Date();
        System.out.println(date);
        //发现问题：1.时间默认返回的json字符串变成了时间戳的格式：1605082109423(不断变化的，从1971开始计算的) Timestamp
        //2.输出的时间不是常用的xxxx-xx-xx，而是Wed Nov 11 16:08:29 CST 2020
        return new ObjectMapper().writeValueAsString(date);

    }

    @GetMapping("/time2")
    @ResponseBody
    //解决上述问题
    public String time2() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //1.让他不返回时间戳，所以我们要关闭时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //2.时间格式化问题！自定日期格式对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //3.让mapper指定时间格式为sdf
        mapper.setDateFormat(sdf);

        Date date = new Date();
        return mapper.writeValueAsString(date);

    }
    //发现问题，重复代码过多，封装成工具类

    @GetMapping("/time3")
    @ResponseBody
    //解决上述问题
    public String time3() throws JsonProcessingException {

        Date date = new Date();
        return JsonUtils.getJson(date);

    }
}
