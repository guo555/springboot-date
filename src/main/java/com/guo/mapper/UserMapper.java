package com.guo.mapper;

import com.guo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {


    List<User> queryUserList();

    User findByUsernameAndPassword(String user_num,String password);

    User findbyname(String username);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}
