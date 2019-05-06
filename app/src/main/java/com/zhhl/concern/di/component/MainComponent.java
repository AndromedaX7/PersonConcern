package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.MainModule;
import com.zhhl.concern.mvp.view.activities.MainActivity;

import dagger.Component;


/**
 * Created by miao on 2018/9/4.
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}