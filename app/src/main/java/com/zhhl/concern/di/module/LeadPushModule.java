package com.zhhl.concern.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.mvp.contract.LeadPushContract;
import com.zhhl.concern.mvp.model.LeadPushModel;
import com.zhhl.concern.tcp.inf.NetInf;
import com.zhhl.concern.tcp.proxy.IPushStaticProxy;

import dagger.Module;
import dagger.Provides;

/**
 * Created by miao on 2018/9/6.
 */

@Module
public class LeadPushModule {
    private LeadPushContract.View view;

    /**
     * 构建LeadPushModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public LeadPushModule(LeadPushContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LeadPushContract.View provideLeadPushView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LeadPushContract.Model provideLeadPushModel(Gson gson, Application application, NetInf.IYdsp iYdsp, NetInf.IPush iPush) {
        return new LeadPushModel(gson, application, iYdsp, new IPushStaticProxy(iPush));
    }
}