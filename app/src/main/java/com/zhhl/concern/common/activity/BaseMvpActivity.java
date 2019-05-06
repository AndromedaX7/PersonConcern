package com.zhhl.concern.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhhl.concern.common.fragment.BaseMvpFragment;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by miao on 2018/6/28.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends UnStructActivity /*implements WebSocketMessageDispatcher*/ {

    public String TAG = getClass().getSimpleName();

//    @Inject
//    protected VpnApi50 vpnApi;

    @Inject
    protected P mPresent;


    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (gaussianBlurredEnabled()) {
            ButterKnife.bind(this);
        }
        onCreated();
    }


    protected void onCreated() {
        if (!gaussianBlurredEnabled()) {
            setLayout();
        }
    }

    protected void setAppComponent(AppComponent appComponent, BaseMvpFragment... fragment) {
        for (BaseMvpFragment f :
                fragment) {
            f.setAppComponent(appComponent);
        }
    }

    @Override
    protected void onDestroy() {
        if (bind != null)
            bind.unbind();
        super.onDestroy();
        mPresent.onDestroy();
//        unRegisterSignal();
    }


    protected void startActivityAndFinish(Class target) {
        startActivity(new Intent(this, target));
        finish();
    }


//    protected void fragmentPalette(Palette palette) {
//        for (Fragment f :
//                fragments) {
//            if (f instanceof IPalette) {
//                ((IPalette) f).palette(palette);
//            }
//
//        }
//    }
//    public void loadMain() {
//        startActivityAndFinish(MainActivity.class);
//    }


    protected boolean gaussianBlurredEnabled() {
        return false;
    }

//    public void registerSignal(SignalBus signalBus) {
//    }

//    public void unRegisterSignal(int... codes) {
//        for (int i = 0; codes != null && i < codes.length; i++) {
//            signalBus.unRegisterHandler(codes[i]);
//        }
//    }


//    public void statusBar(int color) {
//        getWindow().setStatusBarColor(color);
//    }
//
//    public void setNavigationBarColor(int color) {
//        getWindow().setNavigationBarColor(color);
//    }
//
//    public void dispatchMessage(Bundle bundle) {
//    }

//    public void dispatchMessage_onMessage(String message) {
//        for (BaseMvpFragment fr : fragments) {
//            if (fr.isVisible()&&!fr.isHidden()){
//                fr.dispatchMessage_onMessage(message);
//            }
//        }
//        Log.e(TAG, "dispatchMessage_onMessage: " + message);
//    }

}



