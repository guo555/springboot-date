package com.guo.mapper;

import com.guo.pojo.Meetinglist;
import com.guo.pojo.Meetingroom;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ListMapper {

    List<Meetinglist> queryList();

    Meetinglist queryListById(String id);

    List<Meetinglist> queryListByTime(String sdate);

    int addList(Meetinglist meetinglist);

    int updateList(Meetinglist meetinglist);

    int deleteList(String id);
}
