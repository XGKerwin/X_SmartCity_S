package com.example.x_smartcity_s.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  17:06
 */
public class GetActionById {


    /**
     * id : 12
     * typeid : 3
     * time : 2020-10-02 10:36:08
     * name : 安全生产知识竞赛
     * count : 351
     * praiseCount : 133
     * description : 每个事业单位派出一支代表队，代表队由3人组成，采取两两对战方式进行淘汰。
     * image : http://192.168.43.50:8080/mobileA/images/a12.jpg
     * recommand : 0
     * showImage : 0
     */

    private int id;
    private int typeid;
    private String time;
    private String name;
    private int count;
    private int praiseCount;
    private String description;
    private String image;
    private int recommand;
    private int showImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRecommand() {
        return recommand;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public int getShowImage() {
        return showImage;
    }

    public void setShowImage(int showImage) {
        this.showImage = showImage;
    }
}
