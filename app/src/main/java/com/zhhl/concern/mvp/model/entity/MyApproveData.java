package com.zhhl.concern.mvp.model.entity;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by miao on 2018/9/5.
 */
public class MyApproveData implements Comparable<MyApproveData> {
    private String concernUserName;
    private String approvalState;
    private String content;
    private String startDate;
    private String endDate;
    private String approvalId;

    public String getConcernUserName() {
        return concernUserName;
    }

    public void setConcernUserName(String concernUserName) {
        this.concernUserName = concernUserName;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public MyApproveData() {

    }

    /**
     * @param concernUserName
     * @param approvalState
     * @param content
     * @param startDate
     * @param endDate
     * @param approvalId
     */
    public MyApproveData(String concernUserName, String approvalState, String content, String startDate, String endDate, String approvalId) {

        this.concernUserName = concernUserName;
        this.approvalState = approvalState;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approvalId = approvalId;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    @Override
    public int compareTo(@NonNull MyApproveData o) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            long result = dateFormat.parse(this.getEndDate()).getTime() -
                    dateFormat.parse(o.getEndDate()).getTime();

            if (result > 0) return 1;
            if (result < 0) return -1;
            else return 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString() {
        return endDate;
    }
}
