package com.example.x_smartcity_s.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  14:48
 */
public class GetSubwaysByStation {

    /**
     *             "subwayid": 1,
     *             "name": "地铁1号线",
     *             "nextname": "万寿路站",
     *             "time": 49
     */

    private String subwayid,name,nextname,time;

    public String getSubwayid() {
        return subwayid;
    }

    public void setSubwayid(String subwayid) {
        this.subwayid = subwayid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextname() {
        return nextname;
    }

    public void setNextname(String nextname) {
        this.nextname = nextname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
