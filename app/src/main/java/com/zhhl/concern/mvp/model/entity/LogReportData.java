package com.zhhl.concern.mvp.model.entity;

public class LogReportData {

    private String destIp;
    private String logType;
    private String formatParam;
    private String module;
    private String sessionId;

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getFormatParam() {
        return formatParam;
    }

    public void setFormatParam(String formatParam) {
        this.formatParam = formatParam;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LogReportData() {
    }

    public LogReportData(String destIp, String logType, String formatParam, String module, String sessionId, String source, String cardNo, String result, String responseType, String destPort, String response, String terminalIp, String time) {
        this.destIp = destIp;
        this.logType = logType;
        this.formatParam = formatParam;
        this.module = module;
        this.sessionId = sessionId;
        this.source = source;
        this.cardNo = cardNo;
        this.result = result;
        this.responseType = responseType;
        this.destPort = destPort;
        this.response = response;
        this.terminalIp = terminalIp;
        this.time = time;
    }

    private String source;
    private String cardNo;
    private String result;
    private String responseType;;

    private String destPort;
    private String response;
    private String terminalIp;
    private String time;


}
