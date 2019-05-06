package com.zhhl.concern.mvp.presenter;

import android.app.Application;

import com.zhhl.concern.common.di.ActivityScope;
import com.zhhl.concern.common.presenter.BasePresenter;
import com.zhhl.concern.mvp.contract.TrajectoryAnalysisContract;
import com.zhhl.concern.tcp.data.PersonTrajectoryHouse;
import com.zhhl.concern.tcp.data.PersonTrajectoryNoId;
import com.zhhl.concern.tcp.data.PersonTrajectorySZQY;
import com.zhhl.concern.tcp.data.PersonTrajectoryUnLock;

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
 * Created by miao on 2018/10/9.
 */

@ActivityScope
public class TrajectoryAnalysisPresenter extends BasePresenter<TrajectoryAnalysisContract.Model, TrajectoryAnalysisContract.View> {
    private Application mApplication;

    @Inject
    public TrajectoryAnalysisPresenter(TrajectoryAnalysisContract.Model model, TrajectoryAnalysisContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void getPersonTrajectory(String idNumber) {
//        mModel.getPersonTrajectory(idNumber)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::result, this::onError, this::onComplete)
//                .isDisposed();
    }

    public void getPersonTrajectory2(String idNumber) {
//        mModel.getPersonTrajectory2(idNumber)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::result, this::onError, this::onComplete)
//                .isDisposed();
    }

    public void getUnlockTrajectory(String idNumber) {
//        mModel.getUnlockTrajectory(idNumber)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::resultUnlock, this::onError, this::onComplete)
//                .isDisposed();
    }

    public void getSZQYTrajectory(String idNumber) {
//        mModel.getSZQYTrajectory(idNumber)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::resultSZQY, this::onError, this::onComplete)
//                .isDisposed();
    }

    public void getNoIdTrajectory(String idNumber) {
//        mModel.getNoIdTrajectory(idNumber)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::resultNoId, this::onError, this::onComplete)
//                .isDisposed();
    }


    private void result(PersonTrajectoryHouse personTrajectoryHouse) {
        if (personTrajectoryHouse.isSuccess()) {
            mRootView.personTrajectoryHouse(personTrajectoryHouse);
        }
    }

    private void resultUnlock(PersonTrajectoryUnLock personTrajectoryUnLock) {
        if (personTrajectoryUnLock.isSuccess()) {
            mRootView.personTrajectoryUnLock(personTrajectoryUnLock);
        }
    }

    private void resultSZQY(PersonTrajectorySZQY personTrajectorySZQY) {
        if (personTrajectorySZQY.isSuccess()) {
            mRootView.personTrajectorySZQY(personTrajectorySZQY);
        }
    }

    private void resultNoId(PersonTrajectoryNoId personTrajectoryNoId) {
        if (personTrajectoryNoId.isSuccess()) {
            mRootView.personTrajectoryNoId(personTrajectoryNoId);
        }
    }
}
