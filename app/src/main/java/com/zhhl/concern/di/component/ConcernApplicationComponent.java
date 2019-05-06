package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.ConcernApplicationModule;
import com.zhhl.concern.mvp.view.activities.ConcernApplicationActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
@Component(modules = ConcernApplicationModule.class, dependencies = AppComponent.class)
public interface ConcernApplicationComponent {
    void inject(ConcernApplicationActivity activity);
}