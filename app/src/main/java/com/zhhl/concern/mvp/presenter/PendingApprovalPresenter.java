package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.PendingApprovalContract;
import com.zhhl.concern.tcp.data.QueryApprove;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
public class PendingApprovalPresenter extends BasePresenter<PendingApprovalContract.Model, PendingApprovalContract.View> {
    private Application mApplication;

    @Inject
    public PendingApprovalPresenter(PendingApprovalContract.Model model, PendingApprovalContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getViewApproval(ArrayList<QueryApprove.ObjBean> data) {

    }
}
