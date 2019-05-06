package com.zhhl.concern.di.module;

//import com.andromeda.origin.tcp.Api;
//import com.andromeda.origin.tcp.login;
//import com.andromeda.origin.tcp.register;
//import com.andromeda.origin.tcp.search;


import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.tcp.inf.NetInf;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by miao on 2018/6/21.
 */

@Module
public class NetworkModule {

    public static Retrofit retrofitCreator(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Api.__BASED__.__BASED_Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static Retrofit retrofitCreator(OkHttpClient client, String basedUrl) {
        return new Retrofit.Builder()
                .baseUrl(basedUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        if (Api.debug) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
//        }
        return builder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    NetInf.IYdsp provideICountInterface(OkHttpClient client) {
        return retrofitCreator(client,"http://192.168.20.228:7103/").create(NetInf.IYdsp.class);
    }

    @Singleton
    @Provides
    NetInf.ITrajectoryAnalysis provideLoginInterface(OkHttpClient client) {
        return retrofitCreator(client,"http://192.168.20.228:7103/").create(NetInf.ITrajectoryAnalysis.class);
    }

    @Provides
    @Singleton
    NetInf.IModel provideIModelInterface(OkHttpClient client) {
        return retrofitCreator(client, "http://192.168.20.228:7098/").create(NetInf.IModel.class);
    }


    @Provides
    @Singleton
    NetInf.IPush providerIPushInterface(OkHttpClient client){
        return retrofitCreator(client).create(NetInf.IPush.class);
    }
}
