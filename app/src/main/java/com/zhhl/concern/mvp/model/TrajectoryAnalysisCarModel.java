package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisCarContract;
import com.zhhl.concern.tcp.data.CarTrajectoryBayonet;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/10/10.
 */

public class TrajectoryAnalysisCarModel extends BaseModel implements TrajectoryAnalysisCarContract.Model {

    private NetInf.ITrajectoryAnalysis iTrajectoryAnalysis;

    public TrajectoryAnalysisCarModel(Gson gson, Application application, NetInf.ITrajectoryAnalysis iTrajectoryAnalysis) {
        super(application, gson);
        this.iTrajectoryAnalysis = iTrajectoryAnalysis;
    }


//    @Override
//    public Observable<CarTrajectoryBayonet> getCpxx(String cpxx) {
//        return iTrajectoryAnalysis.carTrajectory(cpxx);
//    }

//    @Override
//    public Observable<CarTrajectoryBayonet> getCpxx2(String cpxx) {
//        return iTrajectoryAnalysis.carTrajectory2(cpxx);
//    }
}

