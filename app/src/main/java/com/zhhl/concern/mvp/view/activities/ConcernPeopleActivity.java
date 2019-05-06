package com.zhhl.concern.mvp.view.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerConcernPeopleComponent;
import com.zhhl.concern.di.module.ConcernPeopleModule;
import com.zhhl.concern.mvp.contract.ConcernPeopleContract;
import com.zhhl.concern.mvp.presenter.ConcernPeoplePresenter;
import com.zhhl.concern.mvp.view.adapter.PeopleConcernAdapter;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.MyApproveData;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by miao on 2018/9/5.
 */
public class ConcernPeopleActivity extends BaseMvpActivity<ConcernPeoplePresenter> implements ConcernPeopleContract.View {

    public static final String ACTION = "com.zhhl.activity.ConcernList";

    @BindView(R.id.mConcernPeopleList)
    ListView mConcernPeopleList;
    @BindView(R.id.mAdditionalConcern)
    FloatingActionButton mAdditionalConcern;

    PeopleConcernAdapter peopleConcernAdapter = new PeopleConcernAdapter(new ArrayList<>());

    AlertDialog mTipsDialog;
    ViewHolder viewHolder;
    ArrayList<MyApproveData> tipsData = new ArrayList<>();
    ArrayList<MyApprove.ObjBean> data;
    ArrayList<MyApproveData> data2;
    ArrayList<MyApproveData> pendingApprovalData = new ArrayList<>();
    ArrayList<MyApproveData> approvalData = new ArrayList<>();


    Iterator<MyApproveData> iterator;
    @BindView(R.id.mApprovalBt)
    TextView mApprovalBt;
    @BindView(R.id.mUnApprovalBt)
    TextView mUnApprovalBt;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerConcernPeopleComponent.builder()
                .concernPeopleModule(new ConcernPeopleModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_concern_people;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        mPresent.getMyData(((MyApp) (getApplication())).getCode());

    }


    @OnItemClick(R.id.mConcernPeopleList)
    void onApprovalItemPress(int pos) {
        MyApproveData data = ((PeopleConcernAdapter) mConcernPeopleList.getAdapter()).getData().get(pos);
        Intent intent;
        if (data.getMyApprove().getOverFlag().equals("1")) {
            intent = new Intent(this, PersonTrajectoryAnalysisActivity.class);
            intent.putExtra("idNumber", data.getViewApprove().getIdNumber());

        } else {
            intent = new Intent(ConcernApplicationActivity.ACTION);
            intent.putExtra("carTrajectBayonet", data.getMyApprove());
            intent.putExtra("view", data.getViewApprove());
            //todo 把没审批的数据拿过来 编辑 重新发起审批

        }
        startActivity(intent);
    }

    @OnClick(R.id.mAdditionalConcern)
    void onAdditional() {
        startActivity(new Intent(ConcernApplicationActivity.ACTION));
    }

    @OnClick(R.id.mUnApprovalBt)
    void onPendingApprovalPress() {
        peopleConcernAdapter.setData(pendingApprovalData);
    }

    @OnClick(R.id.mApprovalBt)
    void onApprovalPress() {
        peopleConcernAdapter.setData(approvalData);
    }


    @Override
    public void getDataResponse(List<MyApprove.ObjBean> obj) {
        data = new ArrayList<>(obj);
        for (MyApprove.ObjBean o : obj) {
            mPresent.viewApproval(o.getRequestid());
        }

    }

    @Override
    public void maps(Bundle bundle) {
        result(bundle);
    }

    private void result(Bundle bundle) {
        long threeDay = 24 * 60 * 60 * 1000 * 3;

        tipsData.clear();
        for (MyApprove.ObjBean o : data) {
            ViewApprove.ObjBean bundleItem = bundle.getParcelable(o.getRequestid());
            tipsData.add(new MyApproveData(o, bundleItem));

            Log.e(TAG, "result: " + o.getOverFlag() + "::" + bundleItem.getApproveFlag());
        }
        data2 = new ArrayList<>(tipsData);

        Iterator<com.zhhl.concern.tcp.data.MyApproveData> iterator = tipsData.iterator();
        while (iterator.hasNext()) {
            com.zhhl.concern.tcp.data.MyApproveData next = iterator.next();
            if (next.getMyApprove().getOverFlag().equals("1")) {
                approvalData.add(next);
            } else {
                pendingApprovalData.add(next);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            try {
                Date parse = dateFormat.parse(next.getViewApprove().getAttentionTodate());
                if ((parse.getTime() - threeDay) > dateFormat.parse(dateFormat.format(new Date())).getTime()) {
                    iterator.remove();
                }
            } catch (ParseException e) {
                e.printStackTrace();
                iterator.remove();
            }
        }
        Collections.sort(tipsData);
        this.iterator = tipsData.iterator();
        if (this.iterator.hasNext()) {
            this.iterator.next();
        }
        View view = View.inflate(this, R.layout.dialog_date_tips, null);
        mTipsDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        mTipsDialog.show();
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = mTipsDialog.getWindow().getAttributes();
        params.width = (int) (defaultDisplay.getWidth() * 0.7);
        mTipsDialog.getWindow().setAttributes(params);
        viewHolder = new ViewHolder(view);
        viewHolder.setText(tipsData.get(0).getMyApprove().getTitle());
        mTipsDialog.setCancelable(false);
        peopleConcernAdapter = new PeopleConcernAdapter(data2);
        mConcernPeopleList.setAdapter(peopleConcernAdapter);
    }


    class ViewHolder {
        @BindView(R.id.mDialogClose)
        ImageView mDialogClose;
        @BindView(R.id.mDialogContent)
        TextView mDialogContent;
        @BindView(R.id.mDialogBtn)
        TextView mDialogBtn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setText(String name) {
            mDialogContent.setText(Html.fromHtml("你对【" + name + "】的关注<font color=red>即将到期</font>"));
        }

        @OnClick(R.id.mDialogBtn)
        void onIKnowPress() {
            if (iterator != null && iterator.hasNext()) {
                MyApproveData next = iterator.next();
                viewHolder.setText(next.getViewApprove().getName());
            } else
                mTipsDialog.dismiss();

        }

        @OnClick(R.id.mDialogClose)
        void onClose() {
            mTipsDialog.dismiss();
        }

    }
}
