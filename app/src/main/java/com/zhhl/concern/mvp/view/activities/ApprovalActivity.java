package com.zhhl.concern.mvp.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerApprovalComponent;
import com.zhhl.concern.di.module.ApprovalModule;
import com.zhhl.concern.mvp.contract.ApprovalContract;
import com.zhhl.concern.mvp.presenter.ApprovalPresenter;
import com.zhhl.concern.tcp.data.LogInfo;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by miao on 2018/9/6.
 */

public class ApprovalActivity extends BaseMvpActivity<ApprovalPresenter> implements ApprovalContract.View {

    public static final String ACTION = "com.zhhl.activity.Approval";

    @BindView(R.id.mContent)
    LinearLayout content;
    @BindView(R.id.mPetitionConcertUserName)
    TextView mPetitionConcertUserName;
    @BindView(R.id.mPetitionDate)
    TextView mPetitionDate;
    @BindView(R.id.mPetitionContent)
    TextView mPetitionContent;
    @BindView(R.id.mPetitioner)
    TextView mPetitioner;
    @BindView(R.id.mPetitionTimeRange)
    TextView mPetitionTimeRange;
    @BindView(R.id.mApprovalResult)
    LinearLayout mApprovalResult;
    @BindView(R.id.mApprovalOpinion)
    EditText mApprovalOpinion;
    @BindView(R.id.mApprovalCommit)
    TextView mApprovalCommit;
    @BindView(R.id.mApprovalResultMenuTab)
    TextView mApprovalResultMenuTab;

    private String approvalResult;

    private PopupMenu popupMenu;

    private ProgressDialog mDialog;

    private QueryApprove.ObjBean approval;

    private ViewApprove.ObjBean bundleItem;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerApprovalComponent
                .builder()
                .appComponent(appComponent)
                .approvalModule(new ApprovalModule(this)) //请将ApprovalModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_approval;
    }

    @Override
    protected void onCreated() {
        super.onCreated();

        approval = getIntent().getParcelableExtra("approval");
        bundleItem = getIntent().getParcelableExtra("bundleItem");
        ButterKnife.bind(this);
        popupMenu = new PopupMenu(this, mApprovalResultMenuTab);
        getMenuInflater().inflate(R.menu.menu_approval_result, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            approvalResult = item.getTitle().toString();
            mApprovalResultMenuTab.setText(approvalResult);
            popupMenu.dismiss();
            return true;
        });

        if (approval != null) {
            mPresent.getInfo(approval.getRequestid());
            mPetitionDate.setText(approval.getCreateDate());
            mPetitionConcertUserName.setText(approval.getTitle());
            mPetitionContent.setText(bundleItem.getApplyContent());
            mPetitioner.setText(approval.getApplyPerson());
            mPetitionTimeRange.setText(bundleItem.getAttentionFormdate() + "至" + bundleItem.getAttentionTodate());
        }
    }

    @OnClick(R.id.mApprovalResult)
    void approvalResultPress() {
        popupMenu.show();
    }

    @OnClick(R.id.mApprovalCommit)
    void onApprovalPress() {
        //todo :show dialog and finish
        String userName = mPetitionConcertUserName.getText().toString();
//        if (!userName.equals("")) {
//            userName = userName.substring(1, userName.length() - 1);
//        }
//        String content = mPetitionContent.getText().toString();
//        String petitioner = mPetitioner.getText().toString();
//        String timeRange = mPetitionTimeRange.getText().toString();
        String result = mApprovalResultMenuTab.getText().toString();
        String opinion = mApprovalOpinion.getText().toString();
        if (opinion.equals("")) {
            Toast.makeText(this, "请输入审批意见", Toast.LENGTH_SHORT).show();
        } else {
            if (mDialog == null) mDialog = new ProgressDialog(this);
            mDialog.setMessage("正在提交,请稍后...");
            mDialog.setCancelable(false);
            mDialog.show();
            switch (result) {
                case "同意":
                    mPresent.approvalAgree(approval.getRequestid(), approval.getApproveNodeId(), approval.getApprovePerson(), opinion);
                    break;
                case "不同意":
                    mPresent.approvalRefused(approval.getRequestid(), approval.getApprovePerson(), opinion);
                    break;
                default:
                    Log.e(TAG, "onApprovalPress: " + approval.getRequestid() + "::" + approval.getApproveNodeId() + "::" + approval.getApprovePerson() + "::" + opinion);
                    break;
            }
        }


    }

    @Override
    public void success() {
        mDialog.dismiss();
        showSuccess(mApprovalCommit, () -> startActivity(new Intent(this, MainActivity.class)));
    }

    @Override
    public void failed() {
        mDialog.dismiss();
        showFailed(ApprovalActivity.this::onApprovalPress);
    }

    @Override
    public void initInfo(List<LogInfo.ObjBean> obj) {
        list.clear();
        for (LogInfo.ObjBean b : obj) {
            ApprovalViewHolder v = new ApprovalViewHolder(View.inflate(this, R.layout.approval_opinion, null));

            if (b.getApproveOpinion().equals("发起")) {
                v.mType.setText("发起人:");
                v.opinion.setVisibility(View.GONE);
                v.mTip.setVisibility(View.GONE);
            } else {
                v.mType.setText("审批人:");
                v.opinion.setVisibility(View.VISIBLE);
                v.mTip.setVisibility(View.VISIBLE);
            }

            v.approval.setText(b.getApprovePerson());
            v.opinion.setText(b.getApproveOpinion());
            content.addView(v.getView());
            list.add(v);

        }

    }


    class ApprovalViewHolder {

        @BindView(R.id.mOpinionType)
        TextView mType;
        @BindView(R.id.approval)
        TextView approval;
        @BindView(R.id.opinion)
        TextView opinion;
        @BindView(R.id.mTip)
        TextView mTip;


        private View view;

        ApprovalViewHolder(View view) {
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public View getView() {
            return view;
        }
    }


    private ArrayList<ApprovalViewHolder> list = new ArrayList<>();
}