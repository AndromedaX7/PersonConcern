package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.ApprovalContract;
import com.zhhl.concern.tcp.data.CreateApproveRequest;
import com.zhhl.concern.tcp.data.LogInfo;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;


/**
 * Created by miao on 2018/9/6.
 */

public class ApprovalModel extends BaseModel implements ApprovalContract.Model {

    private final NetInf.IYdsp ydsp;

    public ApprovalModel(Gson gson, Application application, NetInf.IYdsp ydsp) {
        super(application, gson);
        this.ydsp = ydsp;
    }


    @Override
    public Observable<CreateApproveRequest> approvalAgree(String requestid, String approveNodeId, String approvePerson, String opinion) {
        return ydsp.approvalAgree(requestid, approveNodeId, approvePerson, opinion);
    }

    @Override
    public Observable<CreateApproveRequest> approvalRefused(String requestid, String approvePerson, String opinion) {
        return ydsp.refused(requestid, approvePerson, opinion);
    }

    @Override
    public Observable<LogInfo> getApprovalInfo(String requestid) {
        return ydsp.log(requestid);
    }
}

