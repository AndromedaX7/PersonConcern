package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.LeadPushModule;
import com.zhhl.concern.mvp.view.activities.LeadPushActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
@Component(modules = LeadPushModule.class, dependencies = AppComponent.class)
public interface LeadPushComponent {
    void inject(LeadPushActivity activity);
}