package com.zhhl.concern.mvp.contract;

import android.os.Bundle;

import com.zhhl.concern.common.ViewCommon;
import com.zhhl.concern.common.model.ModelCommon;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/10/8.
 */

public interface ConcernPeopleContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends ViewCommon {

        void getDataResponse(List<MyApprove.ObjBean> obj);

        void maps(Bundle bundle);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends ModelCommon {

        Observable<MyApprove> getMyApproval(String code);

        Observable<ViewApprove> viewApproval(String o);
    }

}