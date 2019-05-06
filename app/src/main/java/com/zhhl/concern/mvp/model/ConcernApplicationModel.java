package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.ConcernApplicationContract;
import com.zhhl.concern.tcp.data.CreateApproveRequest;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;


/**
 * Created by miao on 2018/9/6.
 */

public class ConcernApplicationModel extends BaseModel implements ConcernApplicationContract.Model {

    private final NetInf.IYdsp ydsp;

    public ConcernApplicationModel(Gson gson, Application application, NetInf.IYdsp ydsp) {
        super(application, gson);
        this.ydsp = ydsp;
    }


    @Override
    public Observable<CreateApproveRequest> save(String applyContent, String applyPeron, String idNumber, String name, String formDate, String toDate) {
        return ydsp.save(applyContent, applyPeron, idNumber, name, formDate, toDate);
    }

    @Override
    public Observable<Boolean> caseRecord(String idCode) {
        return  ydsp.caseRecord(idCode);

    }

    @Override
    public Observable<CreateApproveRequest> saveFast(String content, String code, String idCode, String name, String startDate, String endDate) {
        return ydsp.save1(content, code, idCode, name, startDate, endDate);
    }
}

