package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.ConcernPeopleModule;
import com.zhhl.concern.mvp.view.activities.ConcernPeopleActivity;

import dagger.Component;

/**
 * Created by miao on 2018/10/8.
 */

@ActivityScope
@Component(modules = ConcernPeopleModule.class, dependencies = AppComponent.class)
public interface ConcernPeopleComponent {
    void inject(ConcernPeopleActivity activity);
}