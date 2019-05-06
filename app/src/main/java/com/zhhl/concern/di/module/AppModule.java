package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//import com.andromeda.origin.common.signal.SignalBus;

/**
 * Created by miao on 2018/5/4.
 */
@Module
public class AppModule {


    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApp() {
        return application;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }


}