package com.zhhl.concern.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.di.module.AppModule;
import com.zhhl.concern.di.module.ClientModule;
import com.zhhl.concern.di.module.NetworkModule;
import com.zhhl.concern.tcp.inf.NetInf;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;


@Singleton
@Component(modules = {AppModule.class, ClientModule.class, NetworkModule.class})
public interface AppComponent {
    Application application();

    Gson gson();


//    VpnApi50 vpn();

    OkHttpClient http();

    NetInf.IYdsp iydsp();

    NetInf.ITrajectoryAnalysis iTrajectoryAnalysis();

    NetInf.IModel iModel();

    NetInf.IPush iPush();


}
