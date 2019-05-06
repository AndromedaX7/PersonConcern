package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.LeadPushContract;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;


/**
 * Created by miao on 2018/9/6.
 */

public class LeadPushModel extends BaseModel implements LeadPushContract.Model {


    private final NetInf.IYdsp iYdsp;
    private final NetInf.IPush iPush;

    public LeadPushModel(Gson gson, Application application, NetInf.IYdsp iYdsp, NetInf.IPush iPush) {
        super(application, gson);
        this.iYdsp = iYdsp;
        this.iPush = iPush;
    }


    @Override
    public Observable<MyApprove> getMyApproval(String code) {
        return iYdsp.myApproval(code);
    }

    @Override
    public Observable<ViewApprove> viewApproval(String requestid) {
        return iYdsp.viewApproval(requestid);
    }

    @Override
    public Observable<PushInfo> push(String sfzh) {
        return iPush.push(sfzh);
    }
}

