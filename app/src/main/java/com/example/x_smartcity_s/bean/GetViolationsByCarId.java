package com.example.x_smartcity_s.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  9:24
 */
public class GetViolationsByCarId {

    /**
     * id : 1
     * carid : 鲁A10001
     * time : 2020-01-02 18:09:11
     * place : 幸福大道路口
     * deductPoints : 2
     * cost : 100
     * status : 1
     * description : 违章停车
     * notification : 100001
     */

    private String id;
    private String carid;
    private String time;
    private String place;
    private String deductPoints;
    private String cost;
    private String status;
    private String description;
    private String notification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDeductPoints() {
        return deductPoints;
    }

    public void setDeductPoints(String deductPoints) {
        this.deductPoints = deductPoints;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
