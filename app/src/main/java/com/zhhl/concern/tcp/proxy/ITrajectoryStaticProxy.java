package com.zhhl.concern.tcp.proxy;


import com.zhhl.concern.app.App;
import com.zhhl.concern.tcp.data.ChangQuDiData;
import com.zhhl.concern.tcp.data.GxclData;
import com.zhhl.concern.tcp.data.GxrData2;
import com.zhhl.concern.tcp.data.SelfTrajectoryData;
import com.zhhl.concern.tcp.inf.ILogUploadImpl;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

public class ITrajectoryStaticProxy implements NetInf.IModel {
    private NetInf.IModel iTrajectory;
    private ILogUploadImpl logUpload;

    public ITrajectoryStaticProxy(NetInf.IModel iTrajectory) {
        this.iTrajectory = iTrajectory;
        this.logUpload = App.app().getLogReport();
    }

    @Override
    public Observable<SelfTrajectoryData> selfTrajectory(String sfzh, String dateform, String to, String type) {
        Observable<SelfTrajectoryData> trajectoryObservable = iTrajectory.selfTrajectory(sfzh, dateform, to, type);
//        logUpload.log("{\"sfzh\":\"" + sfzh + "\"}");
        return trajectoryObservable;
    }

    @Override
    public Observable<ChangQuDiData> frequented(String sfzh, String dateform, String to, String type) {
//        logUpload.log("{\"sfzh\":\"" + sfzh + "\"}");
        return iTrajectory.frequented(sfzh, dateform, to, type);
    }

    @Override
    public Observable<GxclData> trajectoryGxcl(String idNumber) {
//        logUpload.log("{\"sfzh\":\"" + idNumber + "\"}");
        return iTrajectory.trajectoryGxcl(idNumber);
    }

    @Override
    public Observable<GxrData2> trajectoryGxr(String idNumber, String dateform, String dateto) {
//        logUpload.log("{\"sfzh\":\"" + idNumber + "\"}");//见面时拍摄身份证照片、、未见面时场所照片
        return iTrajectory.trajectoryGxr(idNumber, dateform, dateto);
    }

    @Override
    public Observable<Boolean> checkPermission(String jinghao, String dizhi) {
        return iTrajectory.checkPermission(jinghao, dizhi);
    }
}
