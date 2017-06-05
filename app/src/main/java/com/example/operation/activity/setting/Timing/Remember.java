package com.example.operation.activity.setting.Timing;

import java.io.Serializable;

/**
 * 用于存储模式选择顺序的一个类
 */

public class Remember implements Serializable {
    public static int xxmode = 0;//休闲模式位置
    public static int dgmode = 0;//动感模式位置
    public static int lmmode = 0;//浪漫模式位置
    public static int yjmode = 0;//夜间模式位置
    public static int zdymode = 0;//自定义模式位置

    public static int getDgmode() {
        return dgmode;
    }
    public static int getLmmode() {
        return lmmode;
    }
    public static int getXxmode() {
        return xxmode;
    }
    public static int getYjmode() {
        return yjmode;
    }
    public static int getZdymode() {
        return zdymode;
    }


    public static void setDgmode(int dgmode) {
        Remember.dgmode = dgmode;
    }
    public static void setLmmode(int lmmode) {
        Remember.lmmode = lmmode;
    }
    public static void setXxmode(int xxmode) {
        Remember.xxmode = xxmode;
    }
    public static void setYjmode(int yjmode) {
        Remember.yjmode = yjmode;
    }
    public static void setZdymode(int zdymode) {
        Remember.zdymode = zdymode;
    }
}

