package com.zhhl.concern.mvp.presenter;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.MainContract;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/4.
 */

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private Application mApplication;

    private Bundle bundle = new Bundle();
    private int count;
    private int size;
    private int pendingApprovalCount;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getPendingApprove(String approvePerson) {
        Log.e(TAG, "getPendingApprove: "+approvePerson );
        mModel.getPendingApprove(approvePerson)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::result, this::onError, this::onComplete)
                .isDisposed();

    }

    private void result(QueryApprove queryApprove) {
        if (queryApprove.isSuccess()) {
            mRootView.put(queryApprove.getObj());
            size = queryApprove.getObj().size();
        } else {
            mRootView.clear();
        }
    }

    public void viewApproveInfo(String requestid) {
        mModel.viewApproveInfo(requestid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::viewApprove, this::onError, this::onComplete)
                .isDisposed();
    }

    private void viewApprove(ViewApprove viewApprove) {
        count++;
        if (viewApprove.isSuccess() && viewApprove.getObj().size() > 0) {

            ViewApprove.ObjBean objBean = viewApprove.getObj().get(0);
            bundle.putParcelable(objBean.getId(), objBean);
            if (objBean.getApproveFlag().equals("0")) {
                pendingApprovalCount++;
            }
        }

        if (count == size && size > 0) {
            bundle.putInt("pendingApprovalCount", pendingApprovalCount);
            mRootView.maps(bundle);
            count = 0;
            size = 0;
            pendingApprovalCount = 0;
        }
    }
}
