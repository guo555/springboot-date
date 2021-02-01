package com.guo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meetinglist {
    private String id;
    private String listcondition;
    private String teacher;
    private String theme;
    private String mname;
    private String stime;
    private String etime;
    private String sdate;
    private String edate;

}
