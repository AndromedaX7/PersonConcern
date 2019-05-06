package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.PendingApprovalModule;
import com.zhhl.concern.mvp.view.activities.PendingApprovalActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
@Component(modules = PendingApprovalModule.class, dependencies = AppComponent.class)
public interface PendingApprovalComponent {
    void inject(PendingApprovalActivity activity);
}