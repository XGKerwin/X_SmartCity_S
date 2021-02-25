package com.example.x_smartcity_s.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:42
 */
public class GetParkInfor {

    /**
     * parkingid : 1
     * parkName : 德百停车场
     * spaceNum : 500
     * address : 湖滨中大道708号
     * rate : 3元/小时
     * distance : 500
     * isOpen : Y
     * surCarPort : 200
     * rateRefer : 3元/小时  一天最高40元
     */

    private int parkingid;
    private String parkName;
    private int spaceNum;
    private String address;
    private String rate;
    private int distance;
    private String isOpen;
    private int surCarPort;
    private String rateRefer;

    public int getParkingid() {
        return parkingid;
    }

    public void setParkingid(int parkingid) {
        this.parkingid = parkingid;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getSpaceNum() {
        return spaceNum;
    }

    public void setSpaceNum(int spaceNum) {
        this.spaceNum = spaceNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public int getSurCarPort() {
        return surCarPort;
    }

    public void setSurCarPort(int surCarPort) {
        this.surCarPort = surCarPort;
    }

    public String getRateRefer() {
        return rateRefer;
    }

    public void setRateRefer(String rateRefer) {
        this.rateRefer = rateRefer;
    }
}
