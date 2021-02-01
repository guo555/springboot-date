package com.guo.dao;


import com.guo.mapper.ListMapper;
import com.guo.mapper.MdateMapper;
import com.guo.pojo.Mdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MdateDao {
    @Autowired
    private MdateMapper mdateMapper;
    //查询全部日期列表
    public List<Mdate> getMdate(){
        List<Mdate> mdates = mdateMapper.queryMdateList();
        return mdates;
    }

    //添加日期信息
    public void add(Mdate mdate){
        mdateMapper.addMdate(mdate);
    }

    //删除所有数据
    public void delete(){
        mdateMapper.deleteall();
    }

    //删除一条数据
    public void delone(int id){
        mdateMapper.deleteone(id);
    }
}
