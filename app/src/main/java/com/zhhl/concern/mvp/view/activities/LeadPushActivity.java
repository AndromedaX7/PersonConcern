package com.zhhl.concern.mvp.view.activities;

import android.widget.ListView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerLeadPushComponent;
import com.zhhl.concern.di.module.LeadPushModule;
import com.zhhl.concern.mvp.contract.LeadPushContract;
import com.zhhl.concern.mvp.model.entity.LeadPushData;
import com.zhhl.concern.mvp.presenter.LeadPushPresenter;
import com.zhhl.concern.mvp.view.adapter.LeadPushAdapter;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by miao on 2018/9/6.
 */

public class LeadPushActivity extends BaseMvpActivity<LeadPushPresenter> implements LeadPushContract.View {


    public static final String ACTION = "com.zhhl.activity.LeadPush";
    @BindView(R.id.mLeadPushList)
    ListView mLeadPushList;


    private LeadPushAdapter mAdapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLeadPushComponent
                .builder()
                .appComponent(appComponent)
                .leadPushModule(new LeadPushModule(this)) //请将LeadPushModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lead_push;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
//        ArrayList<LeadPushData> data = mPresent.getLeadPush();
        mAdapter = new LeadPushAdapter(new ArrayList<>());
        mLeadPushList.setAdapter(mAdapter);

        mPresent.getMyConcernPerson();
//        mPresent.push("220221197903230110");
    }

    @Override
    public void addData(List<PushInfo.DataBean> data) {
        for (PushInfo.DataBean item : data)
            mAdapter.add(item.getTrackdiscription().split("：")[1], DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", item.getCreatetime()));
//        data.
    }
}