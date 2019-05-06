package com.zhhl.concern.tcp.proxy;

import com.zhhl.concern.app.App;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.tcp.inf.ILogUploadImpl;
import com.zhhl.concern.tcp.inf.NetInf;

import io.reactivex.Observable;

public class IPushStaticProxy implements NetInf.IPush {

    private NetInf.IPush iPush;
    private ILogUploadImpl logReport;

    public IPushStaticProxy(NetInf.IPush iPush) {
        this.iPush = iPush;
        this.logReport = App.app().getLogReport();
    }

    @Override
    public Observable<PushInfo> push(String sfzh) {
//        logReport.log("{\"sfzh\":\"" + sfzh + "\'}");
        return iPush.push(sfzh);
    }
}
