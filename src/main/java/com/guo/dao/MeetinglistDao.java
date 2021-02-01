package com.guo.dao;

import com.guo.mapper.ListMapper;
import com.guo.pojo.Meetinglist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class MeetinglistDao {


    @Autowired
    private ListMapper listMapper;

    //查询全部会议室
    public List<Meetinglist> getMeetingList(){
        List<Meetinglist> meetinglists = listMapper.queryList();
        return meetinglists;

    }

    //添加会议室
    public void add(Meetinglist meetinglist){

        meetinglist.setId(UUID.randomUUID().toString());
        meetinglist.setListcondition("未审核");
        listMapper.addList(meetinglist);
    }

    //通过id查询会议室
    public Meetinglist getListById(String id){
        return listMapper.queryListById(id);
    }


    //管理员审核预约
    public void updateList(Meetinglist meetinglist){
        listMapper.updateList(meetinglist);
    }

    //删除会议室
    public void delete(String id){
        listMapper.deleteList(id);
    }

    //通过时间查询列表
    public List<Meetinglist> getListByTime(String sdate){
        List<Meetinglist> mls = listMapper.queryListByTime(sdate);
        return mls;
    }


}
