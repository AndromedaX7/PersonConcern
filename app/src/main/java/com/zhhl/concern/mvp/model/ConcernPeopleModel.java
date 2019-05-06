package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.ConcernPeopleContract;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/10/8.
 */

public class ConcernPeopleModel extends BaseModel implements ConcernPeopleContract.Model {

    private final NetInf.IYdsp iYdsp;

    public ConcernPeopleModel(NetInf.IYdsp iYdsp, Gson gson, Application application) {
        super(application, gson);
        this.iYdsp = iYdsp;
    }


    @Override
    public Observable<MyApprove> getMyApproval(String code) {
        return iYdsp.myApproval(code);
    }

    @Override
    public Observable<ViewApprove> viewApproval(String o) {
        return iYdsp.viewApproval(o);
    }
}

