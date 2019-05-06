package com.zhhl.concern.tcp.data;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by miao on 2018/10/9.
 */
public class MyApproveData implements Comparable<MyApproveData> {
    public MyApprove.ObjBean getMyApprove() {
        return myApprove;
    }

    public void setMyApprove(MyApprove.ObjBean myApprove) {
        this.myApprove = myApprove;
    }

    public ViewApprove.ObjBean getViewApprove() {
        return viewApprove;
    }

    public void setViewApprove(ViewApprove.ObjBean viewApprove) {
        this.viewApprove = viewApprove;
    }

    public MyApproveData(MyApprove.ObjBean myApprove, ViewApprove.ObjBean viewApprove) {

        this.myApprove = myApprove;
        this.viewApprove = viewApprove;
    }

    private MyApprove.ObjBean myApprove;
    private ViewApprove.ObjBean viewApprove;

    @Override
    public int compareTo(@NonNull MyApproveData o) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            long result = dateFormat.parse(this.getViewApprove().getAttentionTodate()).getTime() -
                    dateFormat.parse(o.getViewApprove().getAttentionTodate()).getTime();
            if (result > 0) return 1;
            if (result < 0) return -1;
            else return 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
