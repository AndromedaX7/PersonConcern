package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisListContract;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;


/**
 * Created by miao on 2018/9/6.
 */

public class TrajectoryAnalysisListModel extends BaseModel implements TrajectoryAnalysisListContract.Model {

    private NetInf.IYdsp iYdsp;

    public TrajectoryAnalysisListModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        super(application, gson);
        this.iYdsp = iYdsp;
    }


    @Override
    public Observable<MyApprove> getApproveInfo(String code) {
        return iYdsp.myApproval(code);
    }

    @Override
    public Observable<ViewApprove> getViewApprove(String requestid) {
        return iYdsp.viewApproval(requestid);
    }
}

