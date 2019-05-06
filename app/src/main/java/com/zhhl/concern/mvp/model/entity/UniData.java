package com.zhhl.concern.mvp.model.entity;

/**
 * Created by miao on 2018/9/6.
 */
public class UniData {

    private String approvalId;
    private String concernUserName;
    private String concernIdCode;
    private String content;
    private String timeRange;
    private String requestUserName;
    private String resulet;

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getConcernUserName() {
        return concernUserName;
    }

    public void setConcernUserName(String concernUserName) {
        this.concernUserName = concernUserName;
    }

    public String getConcernIdCode() {
        return concernIdCode;
    }

    public void setConcernIdCode(String concernIdCode) {
        this.concernIdCode = concernIdCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getRequestUserName() {
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
        this.requestUserName = requestUserName;
    }

    public UniData() {

    }

    public UniData(String approvalId, String concernUserName, String concernIdCode, String content, String timeRange, String requestUserName) {

        this.approvalId = approvalId;
        this.concernUserName = concernUserName;
        this.concernIdCode = concernIdCode;
        this.content = content;
        this.timeRange = timeRange;
        this.requestUserName = requestUserName;
    }

    public void setResulet(String resulet) {
        this.resulet = resulet;
    }

    public String getResulet() {
        return resulet;
    }
}
