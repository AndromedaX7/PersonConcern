package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.ConcernApplicationContract;
import com.zhhl.concern.mvp.model.ConcernApplicationModel;
import com.zhhl.concern.tcp.inf.NetInf;

import dagger.Module;
import dagger.Provides;

/**
 * Created by miao on 2018/9/6.
 */

@Module
public class ConcernApplicationModule {
    private ConcernApplicationContract.View view;

    /**
     * 构建ConcernApplicationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public ConcernApplicationModule(ConcernApplicationContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ConcernApplicationContract.View provideConcernApplicationView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ConcernApplicationContract.Model provideConcernApplicationModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        return new ConcernApplicationModel(gson, application, iYdsp);
    }
}