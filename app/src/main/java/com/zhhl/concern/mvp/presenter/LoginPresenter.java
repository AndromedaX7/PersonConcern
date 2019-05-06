package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.LoginContract;

import javax.inject.Inject;


/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void login(String account, String password) {
        ((MyApp) mApplication).setCode(account);
        ((MyApp) mApplication).setPhone(password);
        mRootView.login();

    }
}
