package com.guo.controller;

import com.guo.dao.MeetingroomDao;
import com.guo.mapper.UserMapper;
import com.guo.pojo.Meetinglist;
import com.guo.pojo.Meetingroom;
import com.guo.dao.MeetingroomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class RoomController {

    @Autowired
    private MeetingroomDao meetingroomDao;


    //用户room页面
    @RequestMapping("/mroom")
    public String room(Model model){
        Collection<Meetingroom> meetingrooms = meetingroomDao.getMeetingroom();
        model.addAttribute("rooms",meetingrooms);
        return "room";
    }
    //用户预约
    @RequestMapping("/userorder/{id}")
    public String test(@PathVariable("id")String id,Model model){
        Meetingroom meetingroom = meetingroomDao.getRoomById(id);
        model.addAttribute("meroom",meetingroom);
        return "user/add";
    }

    @RequestMapping("/userorder1")
    public String test1(@ModelAttribute("mname") String mname, Model model){
        System.out.println("------");
        System.out.println(mname);
        Meetingroom meetingroom = meetingroomDao.getRoomByName(mname);
        model.addAttribute("admsg","此时间段已有老师预约");
        model.addAttribute("meroom",meetingroom);
        return "user/add";
    }

    @RequestMapping("/userorder2")
    public String test2(@ModelAttribute("mname") String mname, Model model){
        Meetingroom meetingroom = meetingroomDao.getRoomByName(mname);
        model.addAttribute("admsg","您预约的日期和时间有误！");
        model.addAttribute("meroom",meetingroom);
        return "user/add";
    }

    //管理员room页面
    @RequestMapping("/adroom")
    public String adminroom(Model model){
        Collection<Meetingroom> meetingrooms = meetingroomDao.getMeetingroom();
        model.addAttribute("adminroom",meetingrooms);
        return "admin/admin-room";
    }

    //管理员跳转到会议室添加页面
    @GetMapping("/addroom")
    public String adminaddroom(Model model){
        return "admin/addroom";
    }

    //管理员提交会议室表单
    @PostMapping("/addfinish")
    public String addroom(Meetingroom meetingroom){
        meetingroomDao.add(meetingroom);
        return "redirect:/adroom";
    }
    //管理员跳转到会议室修改页面
    @GetMapping("/updata/{id}")
    public String updata(@PathVariable("id")String id,Model model){
        Meetingroom meetingroom = meetingroomDao.getRoomById(id);
        model.addAttribute("meroom",meetingroom);
        return "admin/update";
    }

    //管理员完成会议室修改
    @PostMapping("/updatefinish")
    public String updatefinish(Meetingroom meetingroom){
        meetingroomDao.updateRoom(meetingroom);
        return "redirect:/adroom";
    }

    //管理员删除会议室
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")String id){
        meetingroomDao.delete(id);
        return "redirect:/adroom";
    }

}
