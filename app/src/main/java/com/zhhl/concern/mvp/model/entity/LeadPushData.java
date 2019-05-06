package com.zhhl.concern.mvp.model.entity;

/**
 * Created by miao on 2018/9/6.
 */
public class LeadPushData {
    private long time;
    private String title;
    private String content;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LeadPushData() {

    }

    public LeadPushData(long time, String title, String content) {

        this.time = time;
        this.title = title;
        this.content = content;
    }

    public LeadPushData(String content, long time) {
        this.time = time;
        this.title = "线索推送";
        this.content = content;
    }
}
