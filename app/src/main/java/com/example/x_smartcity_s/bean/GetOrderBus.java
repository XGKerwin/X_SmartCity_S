package com.example.x_smartcity_s.bean;

public class GetOrderBus {


    /**
     * id : 296
     * busid : 1
     * name : abc
     * phone : 127283730
     * upsite : 火车站
     * downsite : 中医院
     * travetime : 2020-11-9
     * isPay : N
     */

    private int id;
    private int busid;
    private String name;
    private String phone;
    private String upsite;
    private String downsite;
    private String travetime;
    private String isPay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUpsite() {
        return upsite;
    }

    public void setUpsite(String upsite) {
        this.upsite = upsite;
    }

    public String getDownsite() {
        return downsite;
    }

    public void setDownsite(String downsite) {
        this.downsite = downsite;
    }

    public String getTravetime() {
        return travetime;
    }

    public void setTravetime(String travetime) {
        this.travetime = travetime;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
}
