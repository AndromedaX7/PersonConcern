package com.zhhl.concern.common.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.RxResult;
import com.zhhl.concern.common.ViewCommon;
import com.zhhl.concern.common.model.ModelCommon;

import javax.inject.Inject;

/**
 * Created by miao on 2018/6/28.
 */
public class BasePresenter<M extends ModelCommon, V extends ViewCommon> implements IPresenter, RxResult {
    protected String TAG = getClass().getSimpleName();
    protected Gson gson =new Gson();

    protected V mRootView;

    @Inject
    protected M mModel;


    public BasePresenter(M mModel, V mRootView) {
        this.mModel = mModel;
        this.mRootView = mRootView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        mModel = null;
        mRootView = null;
    }


    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        Context context = MyApp.getContext();
        String msg = "服务器异常";
        if (throwable instanceof java.net.SocketTimeoutException) {
            msg = throwable.getMessage();
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }
}
