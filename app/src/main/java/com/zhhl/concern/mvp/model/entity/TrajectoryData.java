package com.zhhl.concern.mvp.model.entity;

/**
 * Created by miao on 2018/9/5.
 */
public class TrajectoryData {
    private long time;
    private String type;
    private String name;
    private String location;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TrajectoryData() {

    }

    public TrajectoryData(long time, String type, String name, String location) {

        this.time = time;
        this.type = type;
        this.name = name;
        this.location = location;
    }
}
