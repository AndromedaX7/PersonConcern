package com.zhhl.concern.mvp.view.activities;

import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.ListView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerTrajectoryAnalysisCarComponent;
import com.zhhl.concern.di.module.TrajectoryAnalysisCarModule;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisCarContract;
import com.zhhl.concern.mvp.presenter.TrajectoryAnalysisCarPresenter;
import com.zhhl.concern.mvp.view.adapter.TrajectoryCarAdapter;
import com.zhhl.concern.tcp.data.CarTrajectory;
import com.zhhl.concern.tcp.data.CarTrajectoryBayonet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrajectoryAnalysisCar extends BaseMvpActivity<TrajectoryAnalysisCarPresenter> implements TrajectoryAnalysisCarContract.View {

    @BindView(R.id.analysisList)
    ListView analysisList;

    AlertDialog dialog;

    private TrajectoryCarAdapter adapter = new TrajectoryCarAdapter(new ArrayList<>());


    EditText editText;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTrajectoryAnalysisCarComponent.builder()
                .appComponent(appComponent)
                .trajectoryAnalysisCarModule(new TrajectoryAnalysisCarModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_trajectory_analysis_car;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        analysisList.setAdapter(adapter);
        editText = new EditText(this);
        dialog = new AlertDialog.Builder(this)
                .setTitle("请输入车牌号:")
                .setCancelable(false)
                .setView(editText)
                .setPositiveButton("确定", (dialog, which) -> {
                    CharSequence title = getSupportActionBar().getTitle();
                    getSupportActionBar().setTitle(title + ":" + editText.getText().toString().toUpperCase());
                    mPresent.getCPXX(editText.getText().toString().toUpperCase());
                    mPresent.getCPXX2(editText.getText().toString().toUpperCase());
                })
                .setNegativeButton("取消", (dialog, which) -> finish())
                .create();
        dialog.show();
    }


    private void getData() {


//        ArrayList<TrajectoryData> carTrajectBayonet = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            carTrajectBayonet.add(new TrajectoryData(System.currentTimeMillis() - (i * 1000000), "卡口过车", editText.getText().toString(), "156-光谷大街飞跃路"));
//        }
////        adapter.setData();
    }

    @Override
    public void carTrajectBayonet(List<CarTrajectoryBayonet.ObjBean> obj) {

        ArrayList<CarTrajectory> list = new ArrayList<>();
        for (CarTrajectoryBayonet.ObjBean o : obj) {
            list.add(new CarTrajectory(o));
        }
        adapter.addDate(list);
    }
}
