package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisContract;
import com.zhhl.concern.tcp.inf.NetInf;

/**
 * Created by miao on 2018/10/9.
 */

public class TrajectoryAnalysisModel extends BaseModel implements TrajectoryAnalysisContract.Model {


    private NetInf.ITrajectoryAnalysis iTrajectoryAnalysis;

    public TrajectoryAnalysisModel(Gson gson, Application application, NetInf.ITrajectoryAnalysis iTrajectoryAnalysis) {
        super(application, gson);
        this.iTrajectoryAnalysis = iTrajectoryAnalysis;
    }


//    @Override
//    public Observable<PersonTrajectoryHouse> getPersonTrajectory(String idNumber) {
//        return iTrajectoryAnalysis.personTrajectory(idNumber);
//    }
//
//    @Override
//    public Observable<PersonTrajectoryHouse> getPersonTrajectory2(String idNumber) {
//        return iTrajectoryAnalysis.personTrajectory2(idNumber);
//    }

//    @Override
//    public Observable<PersonTrajectoryUnLock> getUnlockTrajectory(String idNumber) {
//        return iTrajectoryAnalysis.unLockTrajectory(idNumber);
//    }
//
//    @Override
//    public Observable<PersonTrajectorySZQY> getSZQYTrajectory(String idNumber) {
//        return iTrajectoryAnalysis.szqyTrajectory(idNumber);
//    }
//
//    @Override
//    public Observable<PersonTrajectoryNoId> getNoIdTrajectory(String idNumber) {
//        return iTrajectoryAnalysis.noIdTrajectory(idNumber);
//    }
}

