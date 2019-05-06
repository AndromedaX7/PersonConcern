package com.zhhl.concern.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/10/8.
 */
public class ViewApprove {
    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"E82A009635AB44EBA28371D79794AF69","name":"请假申请","idNumber":"220183199511111010","attentionFormdate":"2018-08-08","attentionTodate":"2018-12-12","applyContent":"测试","applyPerson":"206589","applyDate":"2018-09-21 10:01:35","approveFlag":"2"}]
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
         * id : E82A009635AB44EBA28371D79794AF69
         * name : 请假申请
         * idNumber : 220183199511111010
         * attentionFormdate : 2018-08-08
         * attentionTodate : 2018-12-12
         * applyContent : 测试
         * applyPerson : 206589
         * applyDate : 2018-09-21 10:01:35
         * approveFlag : 2
         */

        private String id;
        private String name;
        private String idNumber;
        private String attentionFormdate;
        private String attentionTodate;
        private String applyContent;
        private String applyPerson;
        private String applyDate;
        private String approveFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getAttentionFormdate() {
            return attentionFormdate;
        }

        public void setAttentionFormdate(String attentionFormdate) {
            this.attentionFormdate = attentionFormdate;
        }

        public String getAttentionTodate() {
            return attentionTodate;
        }

        public void setAttentionTodate(String attentionTodate) {
            this.attentionTodate = attentionTodate;
        }

        public String getApplyContent() {
            return applyContent;
        }

        public void setApplyContent(String applyContent) {
            this.applyContent = applyContent;
        }

        public String getApplyPerson() {
            return applyPerson;
        }

        public void setApplyPerson(String applyPerson) {
            this.applyPerson = applyPerson;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getApproveFlag() {
            return approveFlag;
        }

        public void setApproveFlag(String approveFlag) {
            this.approveFlag = approveFlag;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.idNumber);
            dest.writeString(this.attentionFormdate);
            dest.writeString(this.attentionTodate);
            dest.writeString(this.applyContent);
            dest.writeString(this.applyPerson);
            dest.writeString(this.applyDate);
            dest.writeString(this.approveFlag);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.idNumber = in.readString();
            this.attentionFormdate = in.readString();
            this.attentionTodate = in.readString();
            this.applyContent = in.readString();
            this.applyPerson = in.readString();
            this.applyDate = in.readString();
            this.approveFlag = in.readString();
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
