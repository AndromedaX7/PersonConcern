package com.zhhl.concern.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.SplashContract;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/7.
 */

public class SplashModel extends BaseModel implements SplashContract.Model {

    private final NetInf.IModel iModel;

    public SplashModel(Gson gson, Application application, NetInf.IModel iModel) {
        super(application, gson);
        this.iModel = iModel;
    }


    @Override
    public Observable<Boolean> checkPermission(String code, String requiredId) {
        Log.e( "checkPermission: ",requiredId );
        return iModel.checkPermission(code, requiredId);
    }
}

