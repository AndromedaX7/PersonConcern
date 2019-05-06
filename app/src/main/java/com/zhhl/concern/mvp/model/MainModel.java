package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.MainContract;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/4.
 */

public class MainModel extends BaseModel implements MainContract.Model {

    private final NetInf.IYdsp iYdsp;

    public MainModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        super(application, gson);
        this.iYdsp = iYdsp;
    }

    @Override
    public Observable<QueryApprove> getPendingApprove(String approvePerson) {
        return iYdsp.approvePerson(approvePerson);
    }

    @Override
    public Observable<ViewApprove> viewApproveInfo(String requestid) {
        return iYdsp.viewApproval(requestid);
    }
}

