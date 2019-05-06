package com.zhhl.concern.mvp.view.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerPendingApprovalComponent;
import com.zhhl.concern.di.module.PendingApprovalModule;
import com.zhhl.concern.mvp.contract.PendingApprovalContract;
import com.zhhl.concern.mvp.presenter.PendingApprovalPresenter;
import com.zhhl.concern.mvp.view.fragment.ApprovalFragment;
import com.zhhl.concern.mvp.view.fragment.PendingApprovalFragment;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.util.DialogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by miao on 2018/9/7.
 */

public class PendingApprovalActivity extends BaseMvpActivity<PendingApprovalPresenter> implements PendingApprovalContract.View {

    public static final String ACTION = "com.zhhl.activity.PendingApproval";

    @BindView(R.id.mPendingApprovalTv)
    TextView mPendingApprovalTv;
    @BindView(R.id.mPendingApprovalBar)
    LinearLayout mPendingApprovalBar;
    @BindView(R.id.mApprovalTv)
    TextView mApprovalTv;
    @BindView(R.id.mApprovalBar)
    LinearLayout mApprovalBar;
    @BindView(R.id.centerView)
    View centerView;
    @BindView(R.id.mConcernApplicationContent)
    FrameLayout mConcernApplicationContent;
    @BindView(R.id.mPendingApprovalLL)
    LinearLayout mPendingApprovalLL;
    @BindView(R.id.mApprovalLL)
    LinearLayout mApprovalLL;


    private Bundle bundle;

    private PendingApprovalFragment pendingApprovalFragment = PendingApprovalFragment.newInstance();
    private ApprovalFragment approvalFragment = ApprovalFragment.newInstance();

    private ArrayList<QueryApprove.ObjBean> pendingApproval = new ArrayList<>();
    private ArrayList<QueryApprove.ObjBean> approval = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerPendingApprovalComponent
                .builder()
                .appComponent(appComponent)
                .pendingApprovalModule(new PendingApprovalModule(this)) //请将PendingApprovalModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.pending_approval;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        addFragmentAndHidden(mConcernApplicationContent.getId(), false,
                pendingApprovalFragment,
                approvalFragment
        );

        ArrayList<QueryApprove.ObjBean> data = getIntent().getParcelableArrayListExtra("carTrajectBayonet");

        boolean flag = getIntent().getBooleanExtra("hasBundle", false);
        if (flag) {
            bundle = getIntent().getBundleExtra("bundle");
        } else {
            mPresent.getViewApproval(data);
        }


        for (QueryApprove.ObjBean b : data) {

            ViewApprove.ObjBean o = bundle.getParcelable(b.getRequestid());
            if (o.getApproveFlag().equals("2") || o.getApproveFlag().equals("3")) {
                approval.add(b);
            } else {
                pendingApproval.add(b);
            }
        }

        pendingApprovalFragment.setData(pendingApproval);
        pendingApprovalFragment.putBundle(bundle);
        approvalFragment.setData(approval);
        approvalFragment.putBundle(bundle);
        onPendingApprovalPress();

        /*if (data.size() == 0) {
            AlertDialog tips = DialogUtils.tips(this, "好像没有数据呢,稍后将自动关闭");
            tips.show();
            mApprovalLL.postDelayed(() -> {
                tips.dismiss();
                finish();
            }, 3000);
        }*/
    }

    @OnClick(R.id.mPendingApprovalLL)
    void onPendingApprovalPress() {
        mPendingApprovalTv.setTextColor(getResources().getColor(R.color.colorBlueTextBar));
        mPendingApprovalBar.setBackgroundColor(getResources().getColor(R.color.colorBlueTextBar));
        mApprovalTv.setTextColor(getResources().getColor(R.color.colorTextDefault));
        mApprovalBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        showFragment(pendingApprovalFragment);
    }

    @OnClick(R.id.mApprovalLL)
    void onApprovalPress() {
        mApprovalTv.setTextColor(getResources().getColor(R.color.colorBlueTextBar));
        mApprovalBar.setBackgroundColor(getResources().getColor(R.color.colorBlueTextBar));
        mPendingApprovalTv.setTextColor(getResources().getColor(R.color.colorTextDefault));
        mPendingApprovalBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        showFragment(approvalFragment);
    }
}