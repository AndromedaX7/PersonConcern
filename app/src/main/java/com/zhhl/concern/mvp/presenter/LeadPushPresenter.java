package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.app.App;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.mvp.contract.LeadPushContract;
import com.zhhl.concern.mvp.model.entity.LeadPushData;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
public class LeadPushPresenter extends BasePresenter<LeadPushContract.Model, LeadPushContract.View> {
    private Application mApplication;

    @Inject
    public LeadPushPresenter(LeadPushContract.Model model, LeadPushContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getMyConcernPerson() {

        mModel.getMyApproval(((MyApp) mApplication).getCode())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::myApproval, this::onError, this::onComplete)
                .isDisposed();


    }

    private void myApproval(MyApprove myApprove) {
        if (myApprove.isSuccess()) {
            List<MyApprove.ObjBean> obj = myApprove.getObj();
            for (MyApprove.ObjBean data : obj) {
                mModel.viewApproval(data.getRequestid())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::viewApproval, this::onError, this::onComplete)
                        .isDisposed();

            }
        }
        App.app().getLogReport().log("{\"applyPerson\":\"" + App.app().getUserInfo().getUserInfo().getCode() + "\"}", Api.myApproval, gson.toJson(myApprove));
    }

    private void viewApproval(ViewApprove viewApprove) {//
        //System.currentTimeMillis() < DateUtil.parseDate("yyyy-MM-dd", viewApprove.getObj().get(0).getAttentionTodate())
        if (viewApprove.isSuccess() && viewApprove.getObj().size() > 0 &&DateUtil.parseDate("yyyy-MM-dd", viewApprove.getObj().get(0).getAttentionTodate()) >= DateUtil.parseDate("yyyy-MM-dd" ,DateUtil.format("yyyy-MM-dd",System.currentTimeMillis())) )
            push(viewApprove.getObj().get(0).getIdNumber());
    }


    public void push(String idNumber) {
        mModel.push(idNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::pushData, this::onError, this::onComplete)
                .isDisposed();
    }

    private void pushData(PushInfo pushInfo) {
        mRootView.addData(pushInfo.getData());
    }
}
