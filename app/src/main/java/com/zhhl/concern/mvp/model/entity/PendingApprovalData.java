//package com.zhhl.jinlinpeoconcern.mvp.model.entity;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * Created by miao on 2018/9/5.
// */
//public class PendingApprovalData implements Parcelable {
//
//    private String concernUserName;
//    private String content;
//    private String date;
//
//    private String petitioner;
//    private String state;
//
//    public String getConcernUserName() {
//        return concernUserName;
//    }
//
//    public void setConcernUserName(String concernUserName) {
//        this.concernUserName = concernUserName;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getPetitioner() {
//        return petitioner;
//    }
//
//    public void setPetitioner(String petitioner) {
//        this.petitioner = petitioner;
//    }
//
//    public PendingApprovalData() {
//
//    }
//
//    public PendingApprovalData(String concernUserName, String content, String date, String petitioner) {
//
//        this.concernUserName = concernUserName;
//        this.content = content;
//        this.date = date;
//        this.petitioner = petitioner;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.concernUserName);
//        dest.writeString(this.content);
//        dest.writeString(this.date);
//        dest.writeString(this.petitioner);
//    }
//
//    protected PendingApprovalData(Parcel in) {
//        this.concernUserName = in.readString();
//        this.content = in.readString();
//        this.date = in.readString();
//        this.petitioner = in.readString();
//    }
//
//    public static final Parcelable.Creator<PendingApprovalData> CREATOR = new Parcelable.Creator<PendingApprovalData>() {
//        @Override
//        public PendingApprovalData createFromParcel(Parcel source) {
//            return new PendingApprovalData(source);
//        }
//
//        @Override
//        public PendingApprovalData[] newArray(int size) {
//            return new PendingApprovalData[size];
//        }
//    };
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getState() {
//        return state;
//    }
//}
