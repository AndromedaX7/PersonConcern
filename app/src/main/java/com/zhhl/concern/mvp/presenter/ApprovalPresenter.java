package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.app.App;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.mvp.contract.ApprovalContract;
import com.zhhl.concern.tcp.data.CreateApproveRequest;
import com.zhhl.concern.tcp.data.LogInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
public class ApprovalPresenter extends BasePresenter<ApprovalContract.Model, ApprovalContract.View> {
    private Application mApplication;

    @Inject
    public ApprovalPresenter(ApprovalContract.Model model, ApprovalContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }

    public void approvalAgree(String requestid, String approveNodeId, String approvePerson, String opinion) {
        mModel.approvalAgree(requestid, approveNodeId, approvePerson, opinion)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((data) -> this.result(data, true, requestid, approvePerson, opinion), this::onError, this::onComplete)
                .isDisposed();
    }

    private void result(CreateApproveRequest createApproveRequest, boolean agree, String requestid,
                        String approvePerson,
                        String approveOpinion) {
        if (createApproveRequest.isSuccess())
            mRootView.success();
        else
            mRootView.failed();

        App.app().getLogReport().log("{\n" +
                "  \"requestid\": \"" + requestid + "\",\n" +
                "  \"approvePerson\": \"" + approvePerson + "\",\n" +
                "  \"approveOpinion\": \"" + approveOpinion + "\"\n" +
                "}","3", gson.toJson(createApproveRequest));

    }

    public void approvalRefused(String requestid, String approvePerson, String opinion) {
        mModel.approvalRefused(requestid, approvePerson, opinion)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((data) -> this.result(data, false, requestid, approvePerson, opinion), this::onError, this::onComplete)
                .isDisposed();
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        mRootView.failed();
    }

    public void getInfo(String requestid) {
        mModel.getApprovalInfo(requestid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::infoResult, this::onError, this::onComplete)
                .isDisposed();
    }

    private void infoResult(LogInfo logInfo) {
        if (logInfo.isSuccess()) {
            mRootView.initInfo(logInfo.getObj());
        }
    }
}
