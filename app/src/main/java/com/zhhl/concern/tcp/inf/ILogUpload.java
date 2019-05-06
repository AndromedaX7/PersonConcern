package com.zhhl.concern.tcp.inf;

import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.mvp.model.entity.LogReportData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ILogUpload {



    @POST(Api.logReport)
    @Headers("content-type:application/json")
    Observable<Object> upload(@Body LogReportData data);
}
