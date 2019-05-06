package com.zhhl.concern.common.tcp;

public interface Api {
    boolean debug = true;
    String logReport = "/police/log/save";
    String caseRecord = "http://192.168.20.228:7098/jeesite/jinghao/andi";


    interface __BASED__ {
//        String __BASED_Url = "http://192.168.20.228:7096/";
        String __BASED_Url = "http://192.168.20.228:7103/";
    }

    /**
     * 1、创建发起审批请求
     */
    String save = "/police/ydsp/save";
    String save1 = "/police/ydsp/save1";
    /**
     * 2、审批人查询代办
     */
    String approvePerson = "/police/ydspsq/findInfoByApprovePerson";
    /**
     * 审批-同意
     */
    String approvalArgee = "/police/ydspsq/updateInfo";
    /**
     * 4、查看日志log
     */
    String log = "/police/ydsplog/findInfoByRequestid";
    /**
     * 5、审批-拒绝
     */
    String refused = "/police/ydspsq/updateBackInfo";

    /**
     * 6、申请人我的申请
     */
    String myApproval = "/police/ydspsq/findInfoByAppLyPerson";

    /**
     * 7、查看申请审批信息
     */
    String viewApproval = "/police/ydsp/findInfoById";
//
//
//
//    String save = "/ydsp/save";
//    String save1 = "/ydsp/save1";
//    /**
//     * 2、审批人查询代办
//     */
//    String approvePerson = "/ydspsq/findInfoByApprovePerson";
//    /**
//     * 审批-同意
//     */
//    String approvalArgee = "/ydspsq/updateInfo";
//    /**
//     * 4、查看日志log
//     */
//    String log = "/ydsplog/findInfoByRequestid";
//    /**
//     * 5、审批-拒绝
//     */
//    String refused = "/ydspsq/updateBackInfo";
//
//    /**
//     * 6、申请人我的申请
//     */
//    String myApproval = "/ydspsq/findInfoByAppLyPerson";
//
//    /**
//     * 7、查看申请审批信息
//     */
//    String viewApproval = "/ydsp/findInfoById";
//


//    /**
//     * 1、查询车辆信息（轨迹）
//     */
//    String carTrajectory = "/police/gjfx/findClxxByHPHM";
//    /**
//     * 2、查询人员信息（轨迹）
//     */
//    String personTrajectory = "/police/gjfx/findRyxxByGMSFHM";
//
//    /**
//     * 3、查询车辆信息（企业微信数据）
//     */
//    String carTrajectory2 = "/police/gjfx/findClxxByCarnumber";
//
//    /**
//     * 4、查询人员信息（企业微信数据）
//     */
//    String personTrajectory2 = "/police/gjfx/findRyxxByIdNumber";
//
//    /**
//     * 5、查询开锁信息（企业微信数据）
//     */
//    String unLockTrajectory = "/police/gjfx/findKsxxByIdNumber";
//
//    /**
//     * 6、查询散装汽油（企业微信数据）
//     */
//    String szqyTrajectory = "/police/gjfx/findSzqyByIdNumber";
//    /**
//     * 7、查询无证入住（企业微信数据）
//     */
//    String noIdTrajectory = "/police/gjfx/findWzrzByIdNumber";
//    /**
//     * 8、查询人员车辆信息
//     */
//    String idHasCar = "/police/gjfx/findRyClInfoByIdCard";

    /**
     * 1、查询车辆信息（轨迹）
     */
    String carTrajectory = "/police/gjfx/findClxxByHPHM";
    String carTrajectory3 = "/police/gjfx/findClxxByHPHM1";
    /**
     * 2、查询人员信息（轨迹）
     */
    String personTrajectory = "/police/gjfx/findRyxxByGMSFHM";

    /**
     * 3、查询车辆信息（企业微信数据）
     */
    String carTrajectory2 = "/police/gjfx/findClxxByCarnumber";

    /**
     * 4、查询人员信息（企业微信数据）
     */
    String personTrajectory2 = "/police/gjfx/findRyxxByIdNumber";

    /**
     * 5、查询开锁信息（企业微信数据）
     */
    String unLockTrajectory = "/police/gjfx/findKsxxByIdNumber";

    /**
     * 6、查询散装汽油（企业微信数据）
     */
    String szqyTrajectory = "/police/gjfx/findSzqyByIdNumber";
    /**
     * 7、查询无证入住（企业微信数据）
     */
    String noIdTrajectory = "/police/gjfx/findWzrzByIdNumber";
    /**
     * 8、查询人员车辆信息
     */
    String idHasCar = "/police/gjfx/findRyClInfoByIdCard";
}
