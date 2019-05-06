package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.PendingApprovalContract;
import com.zhhl.concern.mvp.model.PendingApprovalModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PendingApprovalModule {
    private PendingApprovalContract.View view;

    /**
     * 构建PendingApprovalModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public PendingApprovalModule(PendingApprovalContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PendingApprovalContract.View providePendingApprovalView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PendingApprovalContract.Model providePendingApprovalModel(Gson gson, Application application) {
        return new PendingApprovalModel(gson, application);
    }
}