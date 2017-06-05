package com.example.operation.activity.setting.Timing;

/**
 * Created by Liao on 2017/6/1.
 */

public class Timesetting {
    private String name;
    private int time;
    public Timesetting(String name,int time) {
        this.name = name;
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }
}
