package com.guo.controller;

import com.guo.dao.MdateDao;
import com.guo.dao.MeetinglistDao;
import com.guo.pojo.Mdate;
import com.guo.pojo.Meetinglist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rdate")
public class DateController {

    @Autowired
    private MeetinglistDao meetinglistDao;

    @Autowired
    private MdateDao mdateDao;

    @RequestMapping("/r1")
    @ResponseBody
    public List<Mdate> alldate() {
        List<Mdate> mdates = mdateDao.getMdate();
        return mdates;
    }


    @RequestMapping("/r4")//最终受用
    @ResponseBody
    public List<Mdate> alldate1(String sdate) throws ParseException {

        //删除数据库所有信息
        mdateDao.delete();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(sdate);

        //根据时间查询所有指定日期预约列表
        //List<Meetinglist> meetinglists = meetinglistDao.getListByTime(sdate);
        List<Meetinglist> meetinglists = new ArrayList<>();
        List<Meetinglist> passlists = new ArrayList<>();

        //遍历所有预约信息,将查询时间在预约时间之间的数据加入到list中
        List<Meetinglist> mt = meetinglistDao.getMeetingList();
        for(int i=0;i<mt.size();i++){
            Date date2 = sdf.parse(mt.get(i).getSdate());
            Date date3 = sdf.parse(mt.get(i).getEdate());
            if(date1.getTime()<=date3.getTime()&&date1.getTime()>=date2.getTime()) {
                meetinglists.add(mt.get(i));
            }
        }

        //筛选已经批准的预约
        for (int i = 0; i < meetinglists.size(); i++) {
            if (meetinglists.get(i).getListcondition().equals("已批准")) {
                passlists.add(meetinglists.get(i));
            }
        }

        long time[] = {28800000, 32400000, 36000000, 39600000, 43200000, 46800000, 50400000, 54000000, 57600000, 61200000, 64800000, 68400000, 72000000};
        SimpleDateFormat df = new SimpleDateFormat("HH:00");//把字符串转化成时间
        //将meetinglist类型转化为mdate类型
        for (int x = 0; x < passlists.size(); x++) {
            Mdate mdate = new Mdate();
            mdate.setId((int) Math.random());
            mdate.setMname(passlists.get(x).getMname());
            Date sd1 = df.parse(passlists.get(x).getStime());
            Date sd2 = df.parse(passlists.get(x).getEtime());
            long long1 = sd1.getTime() + 28800000;//把时间格式转化成秒数大小
            long long2 = sd2.getTime() + 28800000;
            /*System.out.println(long1);
            System.out.println(long2);*/

            if (time[0] >= long1 && time[0] <= long2) {
                mdate.setA("1");
            } else {
                mdate.setA("0");
            }
            if (time[1] >= long1 && time[1] <= long2) {
                mdate.setB("1");
            } else {
                mdate.setB("0");
            }
            if (time[2] >= long1 && time[2] <= long2) {
                mdate.setC("1");
            } else {
                mdate.setC("0");
            }
            if (time[3] >= long1 && time[3] <= long2) {
                mdate.setD("1");
            } else {
                mdate.setD("0");
            }
            if (time[4] >= long1 && time[4] <= long2) {
                mdate.setE("1");
            } else {
                mdate.setE("0");
            }
            if (time[5] >= long1 && time[5] <= long2) {
                mdate.setF("1");
            } else {
                mdate.setF("0");
            }
            if (time[6] >= long1 && time[6] <= long2) {
                mdate.setG("1");
            } else {
                mdate.setG("0");
            }
            if (time[7] >= long1 && time[7] <= long2) {
                mdate.setH("1");
            } else {
                mdate.setH("0");
            }
            if (time[8] >= long1 && time[8] <= long2) {
                mdate.setI("1");
            } else {
                mdate.setI("0");
            }
            if (time[9] >= long1 && time[9] <= long2) {
                mdate.setJ("1");
            } else {
                mdate.setJ("0");
            }
            if (time[10] >= long1 && time[10] <= long2) {
                mdate.setK("1");
            } else {
                mdate.setK("0");
            }
            if (time[11] >= long1 && time[11] <= long2) {
                mdate.setL("1");
            } else {
                mdate.setL("0");
            }
            if (time[12] >= long1 && time[12] <= long2) {
                mdate.setM("1");
            } else {
                mdate.setM("0");
            }

            mdateDao.add(mdate);

        }

        List<Mdate> mdates = mdateDao.getMdate();//将所有数据取出

        mdateDao.delete();//删除所有数据

        //同一会议室整合到一起
        for(int x=0;x<mdates.size();x++){
            for(int z=x+1;z<mdates.size();z++){
                if(mdates.get(x).getMname().equals(mdates.get(z).getMname())){
                    if(mdates.get(x).getA().equals("0")&&mdates.get(z).getA().equals("1")){
                        mdates.get(x).setA("1");
                    }
                    if(mdates.get(x).getB().equals("0")&&mdates.get(z).getB().equals("1")){
                        mdates.get(x).setB("1");
                    }
                    if(mdates.get(x).getC().equals("0")&&mdates.get(z).getC().equals("1")){
                        mdates.get(x).setC("1");
                    }
                    if(mdates.get(x).getD().equals("0")&&mdates.get(z).getD().equals("1")){
                        mdates.get(x).setD("1");
                    }
                    if(mdates.get(x).getE().equals("0")&&mdates.get(z).getE().equals("1")){
                        mdates.get(x).setE("1");
                    }
                    if(mdates.get(x).getF().equals("0")&&mdates.get(z).getF().equals("1")){
                        mdates.get(x).setF("1");
                    }
                    if(mdates.get(x).getG().equals("0")&&mdates.get(z).getG().equals("1")){
                        mdates.get(x).setG("1");
                    }
                    if(mdates.get(x).getH().equals("0")&&mdates.get(z).getH().equals("1")){
                        mdates.get(x).setH("1");
                    }
                    if(mdates.get(x).getI().equals("0")&&mdates.get(z).getI().equals("1")){
                        mdates.get(x).setI("1");
                    }
                    if(mdates.get(x).getJ().equals("0")&&mdates.get(z).getJ().equals("1")){
                        mdates.get(x).setJ("1");
                    }
                    if(mdates.get(x).getK().equals("0")&&mdates.get(z).getK().equals("1")){
                        mdates.get(x).setK("1");
                    }
                    if(mdates.get(x).getL().equals("0")&&mdates.get(z).getL().equals("1")){
                        mdates.get(x).setL("1");
                    }
                    if(mdates.get(x).getM().equals("0")&&mdates.get(z).getM().equals("1")){
                        mdates.get(x).setM("1");
                    }
                    //删除数据
                    mdates.remove(z);

                }
            }
        }
        //将整合好的数据循环添加进入数据库
        for(int x=0;x<mdates.size();x++){
            mdateDao.add(mdates.get(x));
        }

        List<Mdate> mdates1 = mdateDao.getMdate();//再次遍历数据库

        return mdates1;

    }

    @RequestMapping("/r")
    @ResponseBody
    public List<Mdate> rdate() {
        List<Mdate> list = new ArrayList<>();
        Mdate mdate = new Mdate(31, "南湖7091", "0", "0", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0");
        Mdate mdate1 = new Mdate(32, "南湖7099", "0", "0", "0", "0", "1", "0", "0", "0", "0", "1", "1", "0", "0");
        Mdate mdate2 = new Mdate(313, "南湖7098", "1", "1", "0", "1", "1", "0", "0", "0", "0", "0", "1", "1", "1");
        list.add(mdate);
        list.add(mdate1);
        list.add(mdate2);
        return list;//由于加了@ResponseBody注解，默认返回字符串
    }
}


