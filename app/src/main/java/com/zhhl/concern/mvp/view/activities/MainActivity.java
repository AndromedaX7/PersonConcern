package com.zhhl.concern.mvp.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xdja.sslvpn.api.VpnApi50Impl;
import com.xdja.uaac.api.UaacApi;
import com.xdja.watermarklibrary.WaterMarkUtils;
import com.zhhl.concern.R;
import com.zhhl.concern.app.App;
import com.zhhl.concern.app.LoginBean;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.common.tcp.HttpTools;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerMainComponent;
import com.zhhl.concern.di.module.MainModule;
import com.zhhl.concern.mvp.contract.MainContract;
import com.zhhl.concern.mvp.presenter.MainPresenter;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.inf.ILogUploadImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/4.
 */

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    public static final String ACTION = "com.zhhl.activity.MainActivity";
    @BindView(R.id.mUserIcon)
    ImageView icon;
    @BindView(R.id.mTopView)
    LinearLayout mTopView;
    @BindView(R.id.mUserNameTv)
    TextView mUserNameTv;
    @BindView(R.id.mPoliceCode)
    TextView mPoliceCode;
    @BindView(R.id.mPoliceTitle)
    TextView mPoliceTitle;
    @BindView(R.id.mUserInfoCd)
    CardView mUserInfoCard;
    @BindView(R.id.mUserInfoBg)
    ImageView mUserInfoBg;

    @BindView(R.id.mPendingApprovalBg)
    ImageView mPendingApprovalBg;
    @BindView(R.id.mPendingApprovalTv)
    TextView mPendingApprovalTv;
    @BindView(R.id.mConcernApplicationBg)
    ImageView mConcernApplicationBg;
    @BindView(R.id.mConcernApplicationTv)
    TextView mConcernApplicationTv;
    @BindView(R.id.mTrajectoryAnalysisBg)
    ImageView mTrajectoryAnalysisBg;
    @BindView(R.id.mTrajectoryAnalysisTv)
    TextView mTrajectoryAnalysisTv;
    @BindView(R.id.mConcernListBg)
    ImageView mConcernListBg;
    @BindView(R.id.mConcernListTv)
    TextView mConcernListTv;
    @BindView(R.id.mLeadPushBg)
    ImageView mLeadPushBg;
    @BindView(R.id.mLeadPushTv)
    TextView mLeadPushTv;
    @BindView(R.id.mPendingApprovalCd)
    CardView mPendingApprovalCd;
    @BindView(R.id.mConcernApplicationCd)
    CardView mConcernApplicationCd;
    @BindView(R.id.mTrajectoryAnalysisCd)
    CardView mTrajectoryAnalysisCd;
    @BindView(R.id.mConcernListCd)
    CardView mConcernListCd;
    @BindView(R.id.mLeadPushCd)
    CardView mLeadPushCd;

    private ArrayList<QueryApprove.ObjBean> datas = new ArrayList<>();

    private long lastPressTime;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this)) //请将MainModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        getUserInfo();
        getPendingApprove();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getUserInfo();
        getPendingApprove();
    }

    private void getPendingApprove() {
        mPresent.getPendingApprove(((MyApp) (getApplication())).getCode()/*"17600194545"*//*"16643416141"*//*"15678779990"*/);
    }


    @OnClick(R.id.mPendingApprovalCd)
    void onPendingApprovalCardPress() {

        Intent mPendingApproval = new Intent(PendingApprovalActivity.ACTION);
        mPendingApproval.putParcelableArrayListExtra("carTrajectBayonet", datas);
        mPendingApproval.putExtra("hasBundle", bundle != null);
        if (bundle != null)
            mPendingApproval.putExtra("bundle", bundle);
        startActivity(mPendingApproval);
    }

    @OnClick(R.id.mConcernApplicationCd)
    void onConcernApplicationCardPress() {
        startActivity(new Intent(ConcernApplicationActivity.ACTION));
    }

    @OnClick(R.id.mTrajectoryAnalysisCd)
    void onTrajectoryAnalysisCardPress() {
        startActivity(new Intent(TrajectoryAnalysisListActivity.ACTION));
    }

    @OnClick(R.id.mConcernListCd)
    void onConcernListCardPress() {
        startActivity(new Intent(ConcernPeopleActivity.ACTION));
    }

    @OnClick(R.id.mLeadPushCd)
    void onLeadPushCardPress() {
        startActivity(new Intent(LeadPushActivity.ACTION));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastPressTime <= 2000) {
            super.onBackPressed();
//            vpnApi.stopSafeClient(((MyApp) getApplication()).getMasks());
        } else {
            lastPressTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void put(List<QueryApprove.ObjBean> obj) {
        datas.clear();
        bundle.clear();
        datas.addAll(obj);
        for (QueryApprove.ObjBean b : datas) {
            mPresent.viewApproveInfo(b.getRequestid());
        }
    }

    @Override
    public void maps(Bundle bundle) {
        this.bundle.clear();
        this.bundle.putAll(bundle);
        mPendingApprovalTv.setText(String.valueOf(bundle.getInt("pendingApprovalCount", 0)));
    }

    @Override
    public void clear() {
        mPendingApprovalTv.setText(String.valueOf(0));
        bundle.clear();
        datas.clear();
    }

    private Bundle bundle = new Bundle();


    public static void startCurrent(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void getUserInfo () {
        LoginBean userInfo = ((App) getApplication()).getUserInfo();
        if (userInfo != null) {
            App.app().setLogReport(new ILogUploadImpl(new VpnApi50Impl(this)));
            ((MyApp) getApplication()).setCode(userInfo.getUserInfo().getCode());
            mUserNameTv.setText(userInfo.getUserInfo().getName());
            mPoliceCode.setText(userInfo.getUserInfo().getCode());
        }
    }

}