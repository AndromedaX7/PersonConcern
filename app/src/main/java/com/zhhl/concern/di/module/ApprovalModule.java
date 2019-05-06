package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.ApprovalContract;
import com.zhhl.concern.mvp.model.ApprovalModel;
import com.zhhl.concern.tcp.inf.NetInf;

import dagger.Module;
import dagger.Provides;

/**
 * Created by miao on 2018/9/6.
 */

@Module
public class ApprovalModule {
    private ApprovalContract.View view;

    /**
     * 构建ApprovalModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public ApprovalModule(ApprovalContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ApprovalContract.View provideApprovalView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ApprovalContract.Model provideApprovalModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        return new ApprovalModel(gson, application, iYdsp);
    }
}