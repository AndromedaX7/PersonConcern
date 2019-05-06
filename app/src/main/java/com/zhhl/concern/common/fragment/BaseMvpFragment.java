package com.zhhl.concern.common.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment /*implements IPalette, WebSocketMessageDispatcher*/ {
    protected String TAG = getClass().getSimpleName();
    private Unbinder bind;

    @Inject
    protected P mPresenter;
    //    @Inject
//    SignalBus signalBus;
    private AppComponent appComponent;

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent(appComponent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreated();
    }

    protected abstract int getLayoutRes();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void setupFragmentComponent(AppComponent appComponent);

//    @Override
//    public void palette(Palette palette) {
//
//    }

    protected void onCreated() {

    }

//    @Override
//    public void dispatchMessage_onMessage(String message) {
//        Log.e(TAG, "dispatchMessage_onMessage: " + message);
//    }

//    @Override
//    public void dispatchMessage(Bundle bundle) {
//
//    }

    public void addData(String message) {

    }
}
