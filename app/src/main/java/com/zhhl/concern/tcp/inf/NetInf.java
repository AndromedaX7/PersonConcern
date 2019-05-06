package com.zhhl.concern.tcp.inf;


import com.zhhl.concern.common.tcp.Api;
import com.zhhl.concern.tcp.data.CarTrajectoryBayonet;
import com.zhhl.concern.tcp.data.ChangQuDiData;
import com.zhhl.concern.tcp.data.CreateApproveRequest;
import com.zhhl.concern.tcp.data.GxclData;
import com.zhhl.concern.tcp.data.GxrData2;
import com.zhhl.concern.tcp.data.LogInfo;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.PersonTrajectoryHouse;
import com.zhhl.concern.tcp.data.PersonTrajectoryNoId;
import com.zhhl.concern.tcp.data.PersonTrajectorySZQY;
import com.zhhl.concern.tcp.data.PersonTrajectoryUnLock;
import com.zhhl.concern.tcp.data.PushInfo;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.SelfTrajectoryData;
import com.zhhl.concern.tcp.data.ViewApprove;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/9/3.
 */
public interface NetInf {


    interface IPush {
        @POST("http://192.168.20.228:7098/jeesite/jinghao/ycdanyi")
        @FormUrlEncoded
        Observable<PushInfo> push(@Field("sfzh") String sfzh);
    }

    interface IYdsp {
        /**
         * 1、创建发起审批请求
         *
         * @param applyContent 申请事由
         * @param applyPeron   申请人
         * @param idNumber     身份号
         * @param name         姓名
         * @param formDate     关注起始日期
         * @param toDate       关注结束日期
         */
        @POST(Api.save)
        @FormUrlEncoded
        Observable<CreateApproveRequest> save(@Field("applyContent") String applyContent,
                                              @Field("applyPeron") String applyPeron,
                                              @Field("idNumber") String idNumber,
                                              @Field("name") String name,
                                              @Field("formDate") String formDate,
                                              @Field("toDate") String toDate);

        @POST(Api.save1)
        @FormUrlEncoded
        Observable<CreateApproveRequest> save1(@Field("applyContent") String applyContent,
                                               @Field("applyPeron") String applyPeron,
                                               @Field("idNumber") String idNumber,
                                               @Field("name") String name,
                                               @Field("formDate") String formDate,
                                               @Field("toDate") String toDate);


        /**
         * 2、审批人查询代办
         */
        @FormUrlEncoded
        @POST(Api.approvePerson)
        Observable<QueryApprove> approvePerson(@Field("approvePerson") String approvePerson);

        /**
         * 审批-同意
         *
         * @param requestid      流程id
         * @param approveNodeId  节点id
         * @param approvePerson  审批人
         * @param approveOpinion 审批意见
         */
        @POST(Api.approvalArgee)
        @FormUrlEncoded
        Observable<CreateApproveRequest> approvalAgree(@Field("requestid") String requestid,
                                                       @Field("approveNodeId") String approveNodeId,
                                                       @Field("approvePerson") String approvePerson,
                                                       @Field("approveOpinion") String approveOpinion);

        /**
         * 4、查看日志log
         */
        @POST(Api.log)
        @FormUrlEncoded
        Observable<LogInfo> log(@Field("requestid") String requestid);

        /**
         * 5、审批-拒绝
         * <p>
         *
         * @param requestid      流程id
         * @param approvePerson  审批人
         * @param approveOpinion 审批意见
         */
        @POST(Api.refused)
        @FormUrlEncoded
        Observable<CreateApproveRequest> refused(@Field("requestid") String requestid,
                                                 @Field("approvePerson") String approvePerson,
                                                 @Field("approveOpinion") String approveOpinion);

        /**
         * 6、申请人我的申请
         *
         * @param applyPerson 审批人id
         */
        @FormUrlEncoded
        @POST(Api.myApproval)
        Observable<MyApprove> myApproval(@Field("applyPerson") String applyPerson);

        /**
         * 7、查看申请审批信息
         *
         * @param requestid 流程id
         */

        @POST(Api.viewApproval)
        @FormUrlEncoded
        Observable<ViewApprove> viewApproval(@Field("requestid") String requestid);

        @POST(Api.caseRecord)
        @FormUrlEncoded
        Observable<Boolean> caseRecord(@Field("sfzh") String sfzh);

    }

    interface ITrajectoryAnalysis {

        /**
         * 5、查询开锁信息（企业微信数据）
         */
        @FormUrlEncoded
        @POST(Api.unLockTrajectory)
        Observable<PersonTrajectoryUnLock> unLockTrajectory(@Field("idNumber") String idNumber, @Field("formToDate") String formToDate, @Field("formEndDate") String formEndDate);

        /**
         * 6、查询散装汽油（企业微信数据）
         */
        @FormUrlEncoded
        @POST(Api.szqyTrajectory)
        Observable<PersonTrajectorySZQY> szqyTrajectory(@Field("idNumber") String idNumber, @Field("formToDate") String formToDate, @Field("formEndDate") String formEndDate);

        /**
         * 7、查询无证入住（企业微信数据）
         */
        @POST(Api.noIdTrajectory)
        @FormUrlEncoded
        Observable<PersonTrajectoryNoId> noIdTrajectory(@Field("idNumber") String idNumber, @Field("formToDate") String formToDate, @Field("formEndDate") String formEndDate);

    }

    interface IModel {

        @POST("/jeesite/shangfang/guiji")
        @FormUrlEncoded
        Observable<SelfTrajectoryData> selfTrajectory(@Field("sfzh") String sfzh, @Field("dateform") String dateform, @Field("dateto") String to, @Field("type") String type);

        @POST("/jeesite/shangfang/guiji")
        @FormUrlEncoded
        Observable<ChangQuDiData> frequented(@Field("sfzh") String sfzh, @Field("dateform") String dateform, @Field("dateto") String to, @Field("type") String type);

        @POST("/jeesite/zaitao/cheliang")
        @FormUrlEncoded
        Observable<GxclData> trajectoryGxcl(@Field("sfzh") String idNumber);

        @POST("/jeesite/zaitao/zhixixin")
        @FormUrlEncoded
        Observable<GxrData2> trajectoryGxr(@Field("sfzh") String idNumber, @Field("dateform") String dateform, @Field("dateto") String dateto);


        @POST("/jeesite/jinghao/yanzheng")
        @FormUrlEncoded
        Observable<Boolean> checkPermission(@Field("jinghao") String jinghao, @Field("dizhi") String dizhi);

    }


}
