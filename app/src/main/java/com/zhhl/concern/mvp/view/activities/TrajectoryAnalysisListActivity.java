package com.zhhl.concern.mvp.view.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ListView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerTrajectoryAnalysisListComponent;
import com.zhhl.concern.di.module.TrajectoryAnalysisListModule;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisListContract;
import com.zhhl.concern.mvp.presenter.TrajectoryAnalysisListPresenter;
import com.zhhl.concern.mvp.view.adapter.TrajectoryListAdapter;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.util.DateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;


/**
 * Created by miao on 2018/9/6.
 */

public class TrajectoryAnalysisListActivity extends BaseMvpActivity<TrajectoryAnalysisListPresenter> implements TrajectoryAnalysisListContract.View {

    public static final String ACTION = "com.zhhl.activity.TrajectoryPeopleList";
    @BindView(R.id.mTrajectoryList)
    ListView mTrajectoryList;

    TrajectoryListAdapter adapter = new TrajectoryListAdapter(new ArrayList<>());

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTrajectoryAnalysisListComponent
                .builder()
                .appComponent(appComponent)
                .trajectoryAnalysisListModule(new TrajectoryAnalysisListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        mTrajectoryList.setAdapter(adapter);
        mPresent.getConcernTrajectoryList();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_trajectory_list;
    }


    @OnItemClick(R.id.mTrajectoryList)
    void onItemPress(int position) {
        MyApprove.ObjBean myApproveData = adapter.getData().get(position);
        ViewApprove.ObjBean mapItem = adapter.getMapItem(myApproveData.getRequestid());

        Log.e(TAG, "onItemPress: endDate:"+DateUtil.parseDate("yyyy-MM-dd", mapItem.getAttentionTodate())  );
        Log.e(TAG, "onItemPress: currentDate:"+DateUtil.parseDate("yyyy-MM-dd" ,DateUtil.format("yyyy-MM-dd",System.currentTimeMillis())) );
        if (DateUtil.parseDate("yyyy-MM-dd", mapItem.getAttentionTodate()) >= DateUtil.parseDate("yyyy-MM-dd" ,DateUtil.format("yyyy-MM-dd",System.currentTimeMillis()))) {
            Intent intent = new Intent(this, PersonTrajectoryAnalysisActivity.class);
            intent.putExtra("idNumber", mapItem.getIdNumber());
            startActivity(intent);
        } else {
            Snackbar.make(mTrajectoryList, "被关注人关注时限已过,请重新申请", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void putData(List<MyApprove.ObjBean> obj) {
        Iterator<MyApprove.ObjBean> iterator = obj.iterator();
        while (iterator.hasNext()) {
            MyApprove.ObjBean next = iterator.next();
            if (!next.getOverFlag().equals("1")) {
                iterator.remove();
            } else {
                mPresent.getViewApprove(next.getRequestid());
            }
        }


        adapter.setData(obj);
        if (adapter.getCount() == 0) {
            Snackbar.make(mTrajectoryList, "暂无数据", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void putItem(ViewApprove.ObjBean objBean) {
        adapter.put(objBean.getId(), objBean);
    }
}