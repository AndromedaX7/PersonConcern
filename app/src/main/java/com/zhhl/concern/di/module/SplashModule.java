package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.SplashContract;
import com.zhhl.concern.mvp.model.SplashModel;
import com.zhhl.concern.tcp.inf.NetInf;

import dagger.Module;
import dagger.Provides;


/**
 * Created by miao on 2018/9/7.
 */

@Module
public class SplashModule {
    private SplashContract.View view;

    /**
     * 构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SplashContract.View provideSplashView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SplashContract.Model provideSplashModel(Gson gson, Application application, NetInf.IModel iModel) {
        return new SplashModel(gson, application,iModel);
    }
}