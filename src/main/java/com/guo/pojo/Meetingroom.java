package com.guo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meetingroom {
    private String id;
    private String mname;
    private String roomorder = "预约";
    private int num;
    private String roomcondition;
    private String tip;


}
