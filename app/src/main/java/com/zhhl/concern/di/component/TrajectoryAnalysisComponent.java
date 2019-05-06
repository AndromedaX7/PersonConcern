package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.TrajectoryAnalysisModule;
import com.zhhl.concern.mvp.view.activities.TrajectoryAnalysis;

import dagger.Component;


/**
 * Created by miao on 2018/10/9.
 */

@ActivityScope
@Component(modules = TrajectoryAnalysisModule.class, dependencies = AppComponent.class)
public interface TrajectoryAnalysisComponent {
    void inject(TrajectoryAnalysis activity);
}