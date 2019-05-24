package com.zhhl.concern.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerAppComponent;
import com.zhhl.concern.di.module.AppModule;
import com.zhhl.concern.di.module.ClientModule;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.flutter.app.FlutterApplication;

/**
 * Created by miao on 2018/5/4.
 */
public class MyApp extends FlutterApplication implements Application.ActivityLifecycleCallbacks {

    private List<String> masks = new ArrayList<>();
    public AppComponent appComponent;
    public Gson gson;

    private String phone;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static Context c;

    public static Context getContext() {
        return c;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        c = this;
        registerActivityLifecycleCallbacks(this);
        Log.e("Application", "onCreate: ");
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .clientModule(new ClientModule(/*webSocketMessenger*/))
                .build();
        gson = appComponent.gson();
    }

    private WeakReference<BaseMvpActivity> weakReference;
    private int count = 0;


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        count++;
        Log.e("AtyLefCircleListener", "onActivityCreated: ");
        Log.e(" current:" + activity.getClass().getName(), "ForceGroundActivityCount:" + count);
        if (activity instanceof BaseMvpActivity)
            weakReference = new WeakReference<>((BaseMvpActivity) activity);

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        count--;
        Log.e("CountDes: ", activity.getClass().getName() + count);
        Log.e(" current" + activity.getClass().getName(), "ForceGroundActivityCount:" + count);
        if (count == 0) {
            weakReference = null;
        }
    }

    public List<String> getMasks() {
        return masks;
    }

    public void setMasks(List<String> masks) {
        this.masks.clear();
        this.masks.addAll(masks);
    }
}