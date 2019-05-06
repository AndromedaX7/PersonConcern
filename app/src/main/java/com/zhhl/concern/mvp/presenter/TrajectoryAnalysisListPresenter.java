package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisListContract;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
public class TrajectoryAnalysisListPresenter extends BasePresenter<TrajectoryAnalysisListContract.Model, TrajectoryAnalysisListContract.View> {
    private Application mApplication;

    @Inject
    public TrajectoryAnalysisListPresenter(TrajectoryAnalysisListContract.Model model, TrajectoryAnalysisListContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getConcernTrajectoryList() {
        mModel.getApproveInfo(((MyApp) (mApplication)).getCode())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::result, this::onError, this::onComplete)
                .isDisposed();
    }

    private void result(MyApprove myApprove) {
        if (myApprove.isSuccess())
            mRootView.putData(myApprove.getObj());
    }

    public void getViewApprove(String requestid) {
        mModel.getViewApprove(requestid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::put, this::onError, this::onComplete)
                .isDisposed();
    }

    private void put(ViewApprove viewApprove) {
        if (viewApprove.isSuccess()) {
            if (viewApprove.getObj().size() > 0) {
                ViewApprove.ObjBean objBean = viewApprove.getObj().get(0);
                mRootView.putItem(objBean);

            }
        }
    }
}
