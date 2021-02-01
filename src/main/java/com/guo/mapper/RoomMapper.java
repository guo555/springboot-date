package com.guo.mapper;

import com.guo.pojo.Meetingroom;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface RoomMapper {

    List<Meetingroom> queryRoom();

    Meetingroom queryRoomById(String id);

    Meetingroom queryRoomByName(String mname);

    int addRoom(Meetingroom meetingroom);

    int updateRoom(Meetingroom meetingroom);

    int deleteRoom(String id);

}
