package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.ConcernPeopleContract;
import com.zhhl.concern.mvp.model.ConcernPeopleModel;
import com.zhhl.concern.tcp.inf.NetInf;

import dagger.Module;
import dagger.Provides;

/**
 * Created by miao on 2018/10/8.
 */

@Module
public class ConcernPeopleModule {
    private ConcernPeopleContract.View view;

    /**
     * 构建ConcernPeopleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public ConcernPeopleModule(ConcernPeopleContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ConcernPeopleContract.View provideConcernPeopleView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ConcernPeopleContract.Model provideConcernPeopleModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        return new ConcernPeopleModel(iYdsp, gson, application);
    }
}