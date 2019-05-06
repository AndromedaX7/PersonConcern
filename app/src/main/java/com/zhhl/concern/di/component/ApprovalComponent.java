package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.ApprovalModule;
import com.zhhl.concern.mvp.view.activities.ApprovalActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
@Component(modules = ApprovalModule.class, dependencies = AppComponent.class)
public interface ApprovalComponent {
    void inject(ApprovalActivity activity);
}