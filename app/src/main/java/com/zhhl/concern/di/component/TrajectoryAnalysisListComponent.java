package com.zhhl.concern.di.component;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.di.module.TrajectoryAnalysisListModule;
import com.zhhl.concern.mvp.view.activities.TrajectoryAnalysisListActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/6.
 */

@ActivityScope
@Component(modules = TrajectoryAnalysisListModule.class, dependencies = AppComponent.class)
public interface TrajectoryAnalysisListComponent {
    void inject(TrajectoryAnalysisListActivity activity);
}