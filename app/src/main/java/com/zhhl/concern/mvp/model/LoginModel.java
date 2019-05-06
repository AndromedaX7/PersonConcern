package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.LoginContract;


/**
 * Created by miao on 2018/9/7.
 */

public class LoginModel extends BaseModel implements LoginContract.Model {

    public LoginModel(Gson gson, Application application) {
        super(application, gson);
    }


    @Override
    public void login(String account, String password) {

    }
}

