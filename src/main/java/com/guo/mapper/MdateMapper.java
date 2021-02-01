package com.guo.mapper;

import com.guo.pojo.Mdate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MdateMapper {

    List<Mdate> queryMdateList();

    int addMdate(Mdate mdate);

    int deleteall();

    int deleteone(int id);

}
