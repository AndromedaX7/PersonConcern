package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.xdja.sslvpn.api.VpnApi50Impl;
import com.zhhl.concern.app.App;
import com.zhhl.concern.app.LoginBean;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.common.tcp.HttpTools;
import com.zhhl.concern.mvp.contract.SplashContract;
import com.zhhl.concern.tcp.inf.ILogUploadImpl;
import com.zhhl.concern.tcp.inf.NetInf;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private Application mApplication;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void login(String token) {
        HttpTools.buildLogin() //      todo //
                .login(token)//      todo //
                .observeOn(AndroidSchedulers.mainThread())    //      todo //
                .subscribeOn(Schedulers.io())//      todo //
                .subscribe(this::callResult, this::onError, this::onComplete)//      todo //
                .isDisposed(); //
    }

    public void onError(Throwable throwable) {
        super.onError(throwable);
    }

    private void callResult(LoginBean loginBean) {
        App.app().setUserInfo(loginBean);
        App.app().setLogReport(new ILogUploadImpl(new VpnApi50Impl(mApplication)));
        ((MyApp) mApplication).setCode(loginBean.getUserInfo().getCode());
        mModel.checkPermission(loginBean.getUserInfo().getCode(), mApplication.getPackageName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::checkResult, this::onError, this::onComplete)
                .isDisposed();
//        mRootView.showMain();
    }

    private void checkResult(boolean o) {
        if (o)
            mRootView.showMain();
        else
            mRootView.dismissIndicator();
    }
}
