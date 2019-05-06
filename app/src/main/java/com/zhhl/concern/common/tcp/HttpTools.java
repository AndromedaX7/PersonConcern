package com.zhhl.concern.common.tcp;

import com.zhhl.concern.app.Login;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by miao on 2018/6/21.
 */
public class HttpTools {
    private static OkHttpClient client;
    private static Login sLogin;
    private static HashMap<Class<?>, Object> interfaces = new HashMap<>();

    private static OkHttpClient okHttpClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            client = builder.build();
        }
        return client;
    }

    public static <T> T build(Class<T> classes) {
        T target = (T) interfaces.get(classes);
        if (target == null) {
            target = new Retrofit.Builder()
                    .baseUrl(Api.__BASED__.__BASED_Url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build()
                    .create(classes);
            interfaces.put(classes, target);
        }
        return target;
    }

    public static Login buildLogin() {
        Login target = sLogin;
        if (target == null) {
            target = new Retrofit.Builder()
                    .baseUrl("http://192.168.20.230:8081/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build()
                    .create(Login.class);
            sLogin = target;
        }
        return target;
    }

    public static <T> T build(Class<? extends T> classes, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build()
                .create(classes);
    }
}
