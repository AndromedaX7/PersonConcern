package com.zhhl.concern.tcp.inf;

import android.os.Process;
import android.util.Log;

import com.xdja.sslvpn.api.VpnApi50;
import com.zhhl.concern.app.App;
import com.zhhl.concern.app.LoginBean;
import com.zhhl.concern.common.tcp.HttpTools;
import com.zhhl.concern.mvp.model.entity.LogReportData;
import com.zhhl.concern.util.MacUtils;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ILogUploadImpl {
    private ILogUpload iLogUpload;
    private VpnApi50 vpnApi50;

    public ILogUploadImpl(VpnApi50 vpnApi50) {
        this.iLogUpload = HttpTools.build(ILogUpload.class, "http://192.168.20.228:7103/");//"http://192.168.20.228:7122/"
        this.vpnApi50 = vpnApi50;
    }
//    logType
//    formatParam
//    sessionId
//    source
//    module
//    response
//    cardNo
//    responseType
//    result
//    time
//    terminalIp


    public void log(String params, String logType, String response) {
        LoginBean.UserInfoBean userInfo = App.app().getUserInfo().getUserInfo();
        Log.e("log: ", "pid :" + Process.myPid());
        /**
         * String policeId,
         *  String sn,
         *  String cardNo,
         *  String logType,
         *  String params,
         *  String url,
         *  String terminalIp,
         *  String module,
         *  String formatParam,
         *  String response,
         *  String responseTime,
         *  String sourceIp,
         *  String sourcePort
         */
        if (App.app().getUserInfo() != null)
            if (userInfo != null)//java.security.NoSuchAlgorithmException: The BC provider no longer provides an implementation for CertificateFactory.X.509


                upload("192.168.20.228",logType,params,"重点人员关注",System.currentTimeMillis()+"-"+userInfo.getCode(),
                       "108",userInfo.getIdentifier() ,"成功","1","7103",response,MacUtils.getIPAddress(App.app()),String .valueOf(System.currentTimeMillis()))
//                upload(userInfo.getCode(), ""/*vpnApi50.getDefaultCertSN()*/, userInfo.getIdentifier(),
//                        "12", params, url, MacUtils.getIPAddress(App.app()),
//                        "", "", response, "500", "192.168.20.228",
//                        "7103")
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.computation())
                        .subscribe(this::logres, this::err, this::complete)
                        .isDisposed();
    }

    Observable<Object> upload(String destIp,
                              String logType,
                              String formatParam,
                              String module,
                              String sessionId,
                              String source,
                              String cardNo,
                              String result,
                              String responseType,
                              String destPort,
                              String response,
                              String terminalIp,
                              String time
    ) {
        //String destIp, String logType, String formatParam, String module, String sessionId, String source, String cardNo, String result, String responseType, String destPort, String response, String terminalIp, String terminalIp
        LogReportData logReportData = new LogReportData(destIp, logType, formatParam, module, sessionId, source, cardNo, result, responseType, destPort, response, terminalIp, time);
        return iLogUpload.upload((logReportData));
    }

    private void complete() {

    }

    private void err(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void logres(Object o) {
        Log.e("logReport>>>: ", o.toString());
    }
}
