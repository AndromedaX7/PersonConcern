package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.LoginModule;
import com.zhhl.concern.mvp.view.activities.LoginActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}