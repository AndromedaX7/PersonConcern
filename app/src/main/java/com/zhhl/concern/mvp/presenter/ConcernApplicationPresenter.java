package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.ConcernApplicationContract;
import com.zhhl.concern.tcp.data.CreateApproveRequest;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
public class ConcernApplicationPresenter extends BasePresenter<ConcernApplicationContract.Model, ConcernApplicationContract.View> {
    private Application mApplication;

    @Inject
    public ConcernApplicationPresenter(ConcernApplicationContract.Model model, ConcernApplicationContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }

    public void commitRequest(String applyContent, String applyPeron, String idNumber, String name, String formDate, String toDate) {
        mModel.save(applyContent, applyPeron, idNumber, name, formDate, toDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::result, this::onError2, this::onComplete)
                .isDisposed();
    }

    private void result(CreateApproveRequest createApproveRequest) {
        if (createApproveRequest.isSuccess())
            mRootView.success();
        else {
            mRootView.error();
        }
    }

    private void onError2(Throwable throwable) {
        super.onError(throwable);
        mRootView.error();
    }

    public void caseRecord(String idCode) {
        mModel.caseRecord(idCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(this::caseRecord, this::onError, this::onComplete)
                .isDisposed();

    }

    private void caseRecord(Boolean caseRecord) {
        if (caseRecord)
            mRootView.hasRecord();
        else
            mRootView.noRecord();
    }

    public void commitRequestFast(String content, String code, String idCode, String name, String startDate, String endDate) {
        mModel.saveFast(content, code, idCode, name, startDate, endDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::result, this::onError2, this::onComplete)
                .isDisposed();
    }
}
