package com.guo.dao;

import com.guo.mapper.RoomMapper;
import com.guo.pojo.Meetingroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class MeetingroomDao {

    @Autowired
    private RoomMapper roomMapper;

    //查询全部会议室
    public List<Meetingroom> getMeetingroom(){
        List<Meetingroom> meetingrooms = roomMapper.queryRoom();
        return meetingrooms;

    }

    //添加会议室
    public void add(Meetingroom meetingroom){

        meetingroom.setId(UUID.randomUUID().toString());
        roomMapper.addRoom(meetingroom);
    }

    //通过id查询会议室
    public Meetingroom getRoomById(String id){
        return roomMapper.queryRoomById(id);
    }

    //通过name查询会议室
    public Meetingroom getRoomByName(String mname){return  roomMapper.queryRoomByName(mname);}

    //修改会议室信息
    public void updateRoom(Meetingroom meetingroom){
        roomMapper.updateRoom(meetingroom);
    }

    //删除会议室
    public void delete(String id){
        roomMapper.deleteRoom(id);
    }


}
