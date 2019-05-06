package com.zhhl.concern.mvp.presenter;

import android.app.Application;
import android.os.Bundle;

import com.zhhl.concern.app.App;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.mvp.contract.ConcernPeopleContract;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/10/8.
 */

@ActivityScope
public class ConcernPeoplePresenter extends BasePresenter<ConcernPeopleContract.Model, ConcernPeopleContract.View> {
    private Application mApplication;
    private int count;
    private int viewApprovalCount;
    private int size;

    @Inject
    public ConcernPeoplePresenter(ConcernPeopleContract.Model model, ConcernPeopleContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getMyData(String code) {
        mModel.getMyApproval(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::result, this::onError, this::onComplete)
                .isDisposed();
    }

    private void result(MyApprove myApprove) {
        if (myApprove.isSuccess()) {
            mRootView.getDataResponse(myApprove.getObj());
            size = myApprove.getObj().size();
        }

//        TODO :Y
        App.app().getLogReport().log("{\"applyPerson\":\"" + App.app().getUserInfo().getUserInfo().getCode() + "\"}", Api.myApproval, gson.toJson(myApprove));
    }

    public void viewApproval(String o) {
        mModel.viewApproval(o)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::put, this::onError, this::onComplete)
                .isDisposed();
    }

    private void put(ViewApprove viewApprove) {
        count++;
        if (viewApprove.isSuccess() && viewApprove.getObj().size() > 0) {

            ViewApprove.ObjBean objBean = viewApprove.getObj().get(0);
            bundle.putParcelable(objBean.getId(), objBean);
            if (objBean.getApproveFlag().equals("0")) {
                viewApprovalCount++;
            }
        }

        if (count == size && size > 0) {
            bundle.putInt("viewApprovalCount", viewApprovalCount);
            mRootView.maps(bundle);
            count = 0;
            size = 0;
            viewApprovalCount = 0;
        }
    }

    private Bundle bundle = new Bundle();
}
