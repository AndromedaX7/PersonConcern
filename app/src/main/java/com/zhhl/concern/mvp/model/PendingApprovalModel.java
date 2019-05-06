package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.PendingApprovalContract;

/**
 * Created by miao on 2018/9/7.
 */

public class PendingApprovalModel extends BaseModel implements PendingApprovalContract.Model {

    public PendingApprovalModel(Gson gson, Application application) {
        super(application, gson);
    }


}

