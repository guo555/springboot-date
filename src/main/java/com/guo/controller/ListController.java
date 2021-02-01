package com.guo.controller;

import com.guo.dao.MeetinglistDao;
import com.guo.pojo.Meetinglist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class ListController {
    @Autowired
    private MeetinglistDao meetinglistDao;


    //用户list页面
    @RequestMapping("/mlist")
    public String list(Model model) {
        Collection<Meetinglist> meetinglists = meetinglistDao.getMeetingList();
        model.addAttribute("lists", meetinglists);
        return "list";
    }

    //管理员list页面
    @RequestMapping("/adminlist")
    public String adminlist(Model model) {
        Collection<Meetinglist> meetinglists = meetinglistDao.getMeetingList();
        model.addAttribute("adminlists", meetinglists);
        return "admin/admin-list";
    }


    //用户提交预约表单到管理员审核
    @RequestMapping("/listtoadmin")
    public String orderfinish(Meetinglist meetinglist,RedirectAttributes attributes) throws ParseException, ServletException, IOException {


        //查询该该时间段是否有人已经预约
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("HH:00");//把字符串转化成时间
        Date date1 = sdf.parse(meetinglist.getSdate());//开始日期
        Date date4 = sdf.parse(meetinglist.getEdate());//结束日期
        System.out.println(date1);
        System.out.println(date4);
        Date time1 = df.parse(meetinglist.getStime());//开始时间
        Date time2 = df.parse(meetinglist.getEtime());//结束时间
        long long1 = time1.getTime() + 28800000;//把时间格式转化成秒数大小
        long long2 = time2.getTime() + 28800000;
        if((date1.getTime()>date4.getTime())||(long1>=long2)){
            attributes.addFlashAttribute("mname",meetinglist.getMname());
            return "redirect:/userorder2";
        }


        List<Meetinglist> meetinglists = new ArrayList<>();
        List<Meetinglist> passlists = new ArrayList<>();
        List<Meetinglist> slists = new ArrayList<>();

        //遍历所有预约信息,将查询时间在预约时间之间的数据加入到list中
        List<Meetinglist> mt = meetinglistDao.getMeetingList();
        for (int i = 0; i < mt.size(); i++) {
            Date date2 = sdf.parse(mt.get(i).getSdate());
            Date date3 = sdf.parse(mt.get(i).getEdate());
            if ((date1.getTime() <= date3.getTime() && date1.getTime() >= date2.getTime())
                    ||(date4.getTime()<= date3.getTime() && date4.getTime() >= date2.getTime())) {
                System.out.println(date2);
                System.out.println(date3);
                meetinglists.add(mt.get(i));
            }
        }
        //筛选已经批准的预约
        for (int i = 0; i < meetinglists.size(); i++) {
            if (meetinglists.get(i).getListcondition().equals("已批准")) {
                passlists.add(meetinglists.get(i));
            }
        }
        //筛选教室
        for (int i = 0; i < passlists.size(); i++) {
            if (passlists.get(i).getMname().equals(meetinglist.getMname())) {
                slists.add(passlists.get(i));
            }
        }
        System.out.println("--------------");
        for(int i=0;i<slists.size();i++){
            System.out.println(slists.get(i).getSdate());
            System.out.println(slists.get(i).getEdate());
        }

        System.out.println("------------");

        //筛选时刻
        for (int i = 0; i < slists.size(); i++) {
            Date time3 = df.parse(slists.get(i).getStime());
            Date time4 = df.parse(slists.get(i).getEtime());
            long long3 = time3.getTime() + 28800000;//把时间格式转化成秒数大小
            long long4 = time4.getTime() + 28800000;
            if ((long1>=long3&&long2<=long4)
                    ||(long1<long3&&long2>long3&&long2<=long4)
                    ||(long2>long4&&long1>=long3&&long1<long4)) {
                System.out.println(long1);
                System.out.println(long2);
                System.out.println(long3);
                System.out.println(long4);
                System.out.println("==");
                System.out.println(meetinglist.getMname());
                attributes.addFlashAttribute("mname",meetinglist.getMname());
                return "redirect:/userorder1";
            }

        }
        meetinglistDao.add(meetinglist);
        return "redirect:/mlist";
    }

    @RequestMapping("/listfinish")
    public String finfish(Model model){
        model.addAttribute("admsg","已存在");
        return "user/add";

    }



    //管理员跳转到预约通过页面
    @GetMapping("/listpass/{id}")
    public String listpass(@PathVariable("id")String id){
        Meetinglist meetinglist =meetinglistDao.getListById(id);
        meetinglist.setListcondition("已批准");
        meetinglistDao.updateList(meetinglist);
        return "redirect:/adminlist";
    }

    //管理员跳转预约不通过页面
    @GetMapping("/listnopass/{id}")
    public String listnopass(@PathVariable("id")String id){
        Meetinglist meetinglist =meetinglistDao.getListById(id);
        meetinglist.setListcondition("未批准");
        meetinglistDao.updateList(meetinglist);
        return "redirect:/adminlist";
    }

}
