package com.guo.mapper;

import com.guo.pojo.Admin;
import com.guo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdLoginMapper {
    List<User> queryAdList();

    Admin findByAdnameAndPassword(String username, String password);
}
