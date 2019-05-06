package com.zhhl.concern.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.model.BaseModel;
import com.zhhl.concern.mvp.contract.PersonTrajectoryAnalysisContract;
import com.zhhl.concern.tcp.data.ChangQuDiData;
import com.zhhl.concern.tcp.data.GxclData;
import com.zhhl.concern.tcp.data.GxrData2;
import com.zhhl.concern.tcp.data.PersonTrajectoryNoId;
import com.zhhl.concern.tcp.data.PersonTrajectorySZQY;
import com.zhhl.concern.tcp.data.PersonTrajectoryUnLock;
import com.zhhl.concern.tcp.data.SelfTrajectoryData;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

public class PersonTrajectoryAnalysisModel extends BaseModel implements PersonTrajectoryAnalysisContract.Model {

    private final NetInf.ITrajectoryAnalysis iWx;
    private final NetInf.IModel iMd;

    public PersonTrajectoryAnalysisModel(Application application, Gson gson, NetInf.ITrajectoryAnalysis iWx, NetInf.IModel iMd) {
        super(application, gson);
        this.iWx = iWx;
        this.iMd = iMd;
    }


    @Override
    public Observable<PersonTrajectoryNoId> noId(String idNumber, String start, String end) {
        return iWx.noIdTrajectory(idNumber, start, end);
    }

    @Override
    public Observable<PersonTrajectorySZQY> szqy(String idNumber, String start, String end) {
        return iWx.szqyTrajectory(idNumber, start, end);
    }

    @Override
    public Observable<PersonTrajectoryUnLock> unlock(String idNumber, String start, String end) {
        return iWx.unLockTrajectory(idNumber, start, end);
    }

    @Override
    public Observable<SelfTrajectoryData> trajectory0(String idNumber, String start, String end, String value) {
        return iMd.selfTrajectory(idNumber, start, end, value);
    }

//    @Override
//    public Observable<HunyinGuiji> trajectory1(String idNumber, String start, String end, String value) {
//        return iMd.shangfangguiji1(idNumber, start, end, value);
//    }

//    @Override
//    public Observable<GxrData> trajectoryGxr(String idNumber) {
//        return iMd.guanXiRenGuiji(idNumber);
//    }

    @Override
    public Observable<ChangQuDiData> getChangQuDi(String idNumber, String start, String end, String value) {
        return iMd.frequented(idNumber, start, end, value);
    }

    @Override
    public Observable<GxclData> trajectoryGxcl(String idNumber) {
        return iMd.trajectoryGxcl(idNumber);
    }

    @Override
    public Observable<GxrData2> trajectoryGxr(String idNumber, String start, String end) {
        return iMd.trajectoryGxr(idNumber, start, end);
    }
}

