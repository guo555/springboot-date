package com.guo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/queryroom")
    public List<Map<String,Object>> queryroom(){
        String sql = "select * from meetingroom";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/adduser")
    public String adduser(){
        String sql = "insert into datebook.test(id,name,pass) value (1111,'小王1','qwe')";
        jdbcTemplate.update(sql);
        return "userlist";

    }

    @GetMapping("/updateuser/{id}")
    public String updateuser(@PathVariable("id")int id){
        String sql = "update datebook.test set name=?,pass=? where id="+id;

        //封装
        Object[] objects = new Object[2];
        objects[0]="xiao";
        objects[1]="ming";

        jdbcTemplate.update(sql,objects);
        return "update-ok";

    }
    @GetMapping("/deleteuser/{id}")
    public String deleteuser(@PathVariable("id")int id){
        String sql = "delete from datebook.test where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";

    }

}
