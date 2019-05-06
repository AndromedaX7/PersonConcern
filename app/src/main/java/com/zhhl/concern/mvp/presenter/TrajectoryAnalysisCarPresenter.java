package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisCarContract;
import com.zhhl.concern.tcp.data.CarTrajectoryBayonet;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by miao on 2018/10/10.
 */

@ActivityScope
public class TrajectoryAnalysisCarPresenter extends BasePresenter<TrajectoryAnalysisCarContract.Model, TrajectoryAnalysisCarContract.View> {
    private Application mApplication;

    @Inject
    public TrajectoryAnalysisCarPresenter(TrajectoryAnalysisCarContract.Model model, TrajectoryAnalysisCarContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getCPXX(String cpxx) {
//        mModel.getCpxx(cpxx)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::result, this::onError, this::onComplete)
//                .isDisposed();

    }

    private void result(CarTrajectoryBayonet carTrajectoryBayonet) {
        if (carTrajectoryBayonet.isSuccess()) {
            mRootView.carTrajectBayonet(carTrajectoryBayonet.getObj());
        }
    }

    public void getCPXX2(String cpxx) {
//        mModel.getCpxx2(cpxx)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::result, this::onError, this::onComplete)
//                .isDisposed();
    }
}
