package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisListContract;
import com.zhhl.concern.mvp.model.TrajectoryAnalysisListModel;
import com.zhhl.concern.tcp.inf.NetInf;

import dagger.Module;
import dagger.Provides;

/**
 * Created by miao on 2018/9/6.
 */

@Module
public class TrajectoryAnalysisListModule {
    private TrajectoryAnalysisListContract.View view;

    /**
     * 构建TrajectoryAnalysisListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public TrajectoryAnalysisListModule(TrajectoryAnalysisListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TrajectoryAnalysisListContract.View provideTrajectoryAnalysisListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TrajectoryAnalysisListContract.Model provideTrajectoryAnalysisListModel(Gson gson, Application application, NetInf.IYdsp iYdsp) {
        return new TrajectoryAnalysisListModel(gson, application, iYdsp);
    }
}