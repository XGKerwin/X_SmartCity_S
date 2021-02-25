package com.example.x_smartcity_s;

import android.app.Application;

import org.litepal.LitePal;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  8:49
 */
public class App extends Application {

    /**
     * {name:"杨仪涵",sex:"女",ID:"371402201002078824",birthday:"2010-2-7",tel:"15905346666",address:"八一小区"}
     * {"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
     */

    private static String name,sex,sfz,tel,csrq,dizhi;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        App.name = name;
    }

    public static String getSex() {
        return sex;
    }

    public static void setSex(String sex) {
        App.sex = sex;
    }

    public static String getSfz() {
        return sfz;
    }

    public static void setSfz(String sfz) {
        App.sfz = sfz;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        App.tel = tel;
    }

    public static String getCsrq() {
        return csrq;
    }

    public static void setCsrq(String csrq) {
        App.csrq = csrq;
    }

    public static String getDizhi() {
        return dizhi;
    }

    public static void setDizhi(String dizhi) {
        App.dizhi = dizhi;
    }

    public static String userida;

    public static String getUserida() {
        return userida;
    }

    public static void setUserida(String userida) {
        App.userida = userida;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
