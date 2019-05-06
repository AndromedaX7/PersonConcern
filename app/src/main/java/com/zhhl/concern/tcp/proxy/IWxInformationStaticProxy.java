package com.zhhl.concern.tcp.proxy;


import com.zhhl.concern.app.App;
import com.zhhl.concern.tcp.data.PersonTrajectoryNoId;
import com.zhhl.concern.tcp.data.PersonTrajectorySZQY;
import com.zhhl.concern.tcp.data.PersonTrajectoryUnLock;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.tcp.inf.ILogUploadImpl;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

public class IWxInformationStaticProxy implements NetInf.ITrajectoryAnalysis {
    private NetInf.ITrajectoryAnalysis information;
    private ILogUploadImpl logUpload;

    public IWxInformationStaticProxy(NetInf.ITrajectoryAnalysis iModelInformation) {
        this.information = iModelInformation;
        this.logUpload = App.app().getLogReport();
    }
    @Override
    public Observable<PersonTrajectoryUnLock> unLockTrajectory(String idNumber, String formToDate, String formEndDate) {
//        logUpload.log("{\"idNumber\":\"" + idNumber + "\"}");
        return information.unLockTrajectory(idNumber, formToDate, formEndDate);
    }

    @Override
    public Observable<PersonTrajectorySZQY> szqyTrajectory(String idNumber, String formToDate, String formEndDate) {
//        logUpload.log("{\"idNumber\":\"" + idNumber + "\"}");
        return information.szqyTrajectory(idNumber, formToDate, formEndDate);
    }

    @Override
    public Observable<PersonTrajectoryNoId> noIdTrajectory(String idNumber, String formToDate, String formEndDate) {
//        logUpload.log("{\"idNumber\":\"" + idNumber + "\"}");
        return information.noIdTrajectory(idNumber, formToDate, formEndDate);
    }
}
