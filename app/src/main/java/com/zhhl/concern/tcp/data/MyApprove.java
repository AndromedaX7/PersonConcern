package com.zhhl.concern.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/10/8.
 */
public class MyApprove {
    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"73B9E30C4AF4446082A6E07DF1BC2C72","requestid":"F0001A9AC24648D79347A903B495839E","createDate":"2018-10-08 14:32:43","approvePerson":"17600194545","title":"请假申请","approveNode":"节点一审批","applyPerson":"206589","overFlag":"0","approveNodeId":"1"},{"id":"2E13E06550C443A8B8C4DAD069DF015D","requestid":"B1FEE3197BE14691BF70C8CFA8EADCB4","createDate":"2018-10-08 14:29:24","approvePerson":"17600194545","title":"请假申请","approveNode":"节点一审批","applyPerson":"206589","overFlag":"0","approveNodeId":"1"},{"id":"8E46034A6B784F108F00C161558522C4","requestid":"7ACB9A2145B2406F925E6B550FC29D4D","createDate":"2018-10-08 14:29:07","approvePerson":"17600194545","title":"请假申请","approveNode":"节点一审批","applyPerson":"206589","overFlag":"0","approveNodeId":"1"},{"id":"67D0289332944367933621E952B1FD59","requestid":"E82A009635AB44EBA28371D79794AF69","createDate":"2018-09-21 10:01:35","approvePerson":"16643416141","title":"请假申请","approveNode":"节点二审批","applyPerson":"206589","overFlag":"2","approveNodeId":"2"}]
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

    public static class ObjBean implements Parcelable {
        /**
         * id : 73B9E30C4AF4446082A6E07DF1BC2C72
         * requestid : F0001A9AC24648D79347A903B495839E
         * createDate : 2018-10-08 14:32:43
         * approvePerson : 17600194545
         * title : 请假申请
         * approveNode : 节点一审批
         * applyPerson : 206589
         * overFlag : 0
         * approveNodeId : 1
         */

        private String id;
        private String requestid;
        private String createDate;
        private String approvePerson;
        private String title;
        private String approveNode;
        private String applyPerson;
        private String overFlag;
        private String approveNodeId;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getApproveNode() {
            return approveNode;
        }

        public void setApproveNode(String approveNode) {
            this.approveNode = approveNode;
        }

        public String getApplyPerson() {
            return applyPerson;
        }

        public void setApplyPerson(String applyPerson) {
            this.applyPerson = applyPerson;
        }

        public String getOverFlag() {
            return overFlag;
        }

        public void setOverFlag(String overFlag) {
            this.overFlag = overFlag;
        }

        public String getApproveNodeId() {
            return approveNodeId;
        }

        public void setApproveNodeId(String approveNodeId) {
            this.approveNodeId = approveNodeId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.requestid);
            dest.writeString(this.createDate);
            dest.writeString(this.approvePerson);
            dest.writeString(this.title);
            dest.writeString(this.approveNode);
            dest.writeString(this.applyPerson);
            dest.writeString(this.overFlag);
            dest.writeString(this.approveNodeId);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.id = in.readString();
            this.requestid = in.readString();
            this.createDate = in.readString();
            this.approvePerson = in.readString();
            this.title = in.readString();
            this.approveNode = in.readString();
            this.applyPerson = in.readString();
            this.overFlag = in.readString();
            this.approveNodeId = in.readString();
        }

        public static final Parcelable.Creator<ObjBean> CREATOR = new Parcelable.Creator<ObjBean>() {
            @Override
            public ObjBean createFromParcel(Parcel source) {
                return new ObjBean(source);
            }

            @Override
            public ObjBean[] newArray(int size) {
                return new ObjBean[size];
            }
        };
    }


}
