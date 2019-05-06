package com.zhhl.concern.tcp.data;

import java.util.List;

/**
 * Created by miao on 2018/10/8.
 */
public class LogInfo {

    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"5179C21F357844B5B85A2AEC1B3314C9","requestid":"5BE8C8935FD842E1B14D00B3DD03443B","createDate":"2018-10-11 15:45:10","approvePerson":"15678779990","approveFlag":"2","approveOpinion":"同意 ","overFlag":""},{"id":"E5A55D28117D4F0FAF9F557275359782","requestid":"5BE8C8935FD842E1B14D00B3DD03443B","createDate":"2018-10-11 15:44:37","approvePerson":"16643416141","approveFlag":"1","approveOpinion":"同意","overFlag":null},{"id":"357CA2D77787445E8157DA404F62854B","requestid":"5BE8C8935FD842E1B14D00B3DD03443B","createDate":"2018-10-11 15:43:40","approvePerson":"17600194545","approveFlag":"1","approveOpinion":"同意","overFlag":null},{"id":"3BB20695CA84471BAB6D2DF8879E295D","requestid":"5BE8C8935FD842E1B14D00B3DD03443B","createDate":"2018-10-11 15:43:14","approvePerson":"206589","approveFlag":"0","approveOpinion":"发起","overFlag":null}]
     * attributes : null
     */

    private boolean success;
    private String msg;
    private Object attributes;
    private List<ObjBean> obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * id : 5179C21F357844B5B85A2AEC1B3314C9
         * requestid : 5BE8C8935FD842E1B14D00B3DD03443B
         * createDate : 2018-10-11 15:45:10
         * approvePerson : 15678779990
         * approveFlag : 2
         * approveOpinion : 同意
         * overFlag :
         */

        private String id;
        private String requestid;
        private String createDate;
        private String approvePerson;
        private String approveFlag;
        private String approveOpinion;
        private String overFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRequestid() {
            return requestid;
        }

        public void setRequestid(String requestid) {
            this.requestid = requestid;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getApprovePerson() {
            return approvePerson;
        }

        public void setApprovePerson(String approvePerson) {
            this.approvePerson = approvePerson;
        }

        public String getApproveFlag() {
            return approveFlag;
        }

        public void setApproveFlag(String approveFlag) {
            this.approveFlag = approveFlag;
        }

        public String getApproveOpinion() {
            return approveOpinion;
        }

        public void setApproveOpinion(String approveOpinion) {
            this.approveOpinion = approveOpinion;
        }

        public String getOverFlag() {
            return overFlag;
        }

        public void setOverFlag(String overFlag) {
            this.overFlag = overFlag;
        }
    }
}
