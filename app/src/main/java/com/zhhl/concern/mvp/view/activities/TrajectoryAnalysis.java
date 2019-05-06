package com.zhhl.concern.mvp.view.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerTrajectoryAnalysisComponent;
import com.zhhl.concern.di.module.TrajectoryAnalysisModule;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisContract;
import com.zhhl.concern.mvp.presenter.TrajectoryAnalysisPresenter;
import com.zhhl.concern.mvp.view.adapter.TrajectoryAdapter;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.PersonTrajectory;
import com.zhhl.concern.tcp.data.PersonTrajectoryHouse;
import com.zhhl.concern.tcp.data.PersonTrajectoryNoId;
import com.zhhl.concern.tcp.data.PersonTrajectorySZQY;
import com.zhhl.concern.tcp.data.PersonTrajectoryUnLock;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TrajectoryAnalysis extends BaseMvpActivity<TrajectoryAnalysisPresenter> implements TrajectoryAnalysisContract.View {

    public static final String ACTION = "com.zhhl.activity.TrajectoryAmalysis";

    @BindView(R.id.mTrajectoryYear)
    TextView mTrajectoryYear;
    @BindView(R.id.mTrajectoryName)
    TextView mTrajectoryName;
    @BindView(R.id.mTrajectoryPush)
    TextView mTrajectoryPush;
    @BindView(R.id.mTrajectoryList)
    ListView mTrajectoryList;
    @BindView(R.id.iAll)
    TextView iAll;
    @BindView(R.id.iAddress)
    TextView iAddress;
    @BindView(R.id.iunLock)
    TextView iunLock;
    @BindView(R.id.iOil)
    TextView iOil;
    @BindView(R.id.iZhuzhi)
    TextView iZhuzhi;

    private ArrayList<PersonTrajectory> datas = new ArrayList<>();
    private ArrayList<TextView> views = new ArrayList<>();

    private TrajectoryAdapter adapter = new TrajectoryAdapter(new ArrayList<>());


    private MyApprove.ObjBean data;
    private ViewApprove.ObjBean view;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTrajectoryAnalysisComponent.builder()
                .appComponent(appComponent)
                .trajectoryAnalysisModule(new TrajectoryAnalysisModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_trajectory_analysis;
    }

    @Override
    protected void onCreated() {
        super.onCreated();


        views.add(iAll);
        views.add(iAddress);
        views.add(iunLock);
        views.add(iOil);
        views.add(iZhuzhi);

        operationViewStyle(iAll);
        data = getIntent().getParcelableExtra("carTrajectBayonet");
        view = getIntent().getParcelableExtra("view");
        mTrajectoryList.setAdapter(adapter);
        if (data != null && view != null) {
            String idNumber = view.getIdNumber();
            mTrajectoryName.setText(view.getName());
            mPresent.getPersonTrajectory(idNumber);
            mPresent.getPersonTrajectory2(idNumber);
            mPresent.getUnlockTrajectory(idNumber);
            mPresent.getSZQYTrajectory(idNumber);
            mPresent.getNoIdTrajectory(idNumber);
        }
    }


    @OnClick({R.id.mTrajectoryPush})
    void onPush() {
        startActivity(new Intent(this, TrajectoryAnalysisCar.class));
    }

    @Override
    public void personTrajectoryHouse(PersonTrajectoryHouse personTrajectoryHouse) {
        List<PersonTrajectoryHouse.ObjBean> obj = personTrajectoryHouse.getObj();
        ArrayList<PersonTrajectory> list = new ArrayList<>();
        for (PersonTrajectoryHouse.ObjBean o : obj) {
            list.add(new PersonTrajectory(o));
        }
        adapter.addDate(list);
        datas.clear();
        datas.addAll(adapter.getData());
    }

    @Override
    public void personTrajectoryUnLock(PersonTrajectoryUnLock personTrajectoryUnLock) {
        List<PersonTrajectoryUnLock.ObjBean> obj = personTrajectoryUnLock.getObj();
        ArrayList<PersonTrajectory> list = new ArrayList<>();
        for (PersonTrajectoryUnLock.ObjBean o : obj) {
            list.add(new PersonTrajectory(o));
        }
        adapter.addDate(list);
        datas.clear();
        datas.addAll(adapter.getData());
    }

    @Override
    public void personTrajectorySZQY(PersonTrajectorySZQY personTrajectorySZQY) {
        List<PersonTrajectorySZQY.ObjBean> obj = personTrajectorySZQY.getObj();
        ArrayList<PersonTrajectory> list = new ArrayList<>();
        for (PersonTrajectorySZQY.ObjBean o : obj) {
            list.add(new PersonTrajectory(o));
        }
        adapter.addDate(list);
        datas.clear();
        datas.addAll(adapter.getData());
    }

    @Override
    public void personTrajectoryNoId(PersonTrajectoryNoId personTrajectoryNoId) {
        List<PersonTrajectoryNoId.ObjBean> obj = personTrajectoryNoId.getObj();
        ArrayList<PersonTrajectory> list = new ArrayList<>();
        for (PersonTrajectoryNoId.ObjBean o : obj) {
            list.add(new PersonTrajectory(o));
        }
        adapter.addDate(list);
        datas.clear();
        datas.addAll(adapter.getData());
    }


    @OnClick({R.id.iAll, R.id.iAddress, R.id.iunLock, R.id.iOil, R.id.iZhuzhi})
    public void onViewClicked(View view) {
        int idx = -1;
        switch (view.getId()) {
            case R.id.iAll:
                idx = 0;
                break;
            case R.id.iAddress:
                idx = 1;
                break;
            case R.id.iunLock:
                idx = 2;
                break;
            case R.id.iOil:
                idx = 3;
                break;
            case R.id.iZhuzhi:
                idx = 4;
                break;
        }

        operationViewStyle(view);
        filter(idx);


    }

    private void filter(int idx) {
        Log.e(TAG, "filter: " + Arrays.toString(datas.toArray()));
        if (idx == 0) {
            Log.e(TAG, "filter: " + Arrays.toString(datas.toArray()));
            adapter.setData(datas);
        } else {
            ArrayList<PersonTrajectory> filter = new ArrayList<>();
            filter.addAll(datas);
            Iterator<PersonTrajectory> iterator = filter.iterator();
            while (iterator.hasNext()) {
                PersonTrajectory next = iterator.next();
                if (next.getType() != idx) {
                    iterator.remove();
                }
            }
            Log.e(TAG, "filter: " + Arrays.toString(filter.toArray()));
            adapter.setData(filter);
        }

    }

    private void operationViewStyle(View view) {
        for (int i = 0; i < views.size(); i++) {
            if (view.getId() == views.get(i).getId()) {
                views.get(i).setBackgroundColor(getResources().getColor(R.color.colorBlueTextBar));
                views.get(i).setTextColor(getResources().getColor(R.color.colorWhite));
            } else {
                views.get(i).setBackgroundColor(getResources().getColor(R.color.colorWhite));
                views.get(i).setTextColor(getResources().getColor(R.color.colorBlueTextBar));
            }
        }
    }
}
