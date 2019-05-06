package com.zhhl.concern.common.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xdja.watermarklibrary.WaterMarkUtils;
import com.zhhl.concern.R;
import com.zhhl.concern.app.App;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.CallbackDialog;
import com.zhhl.concern.di.component.AppComponent;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by miao on 2018/9/3.
 */
public abstract class UnStructActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    protected ArrayList<Fragment> fragments = new ArrayList<>();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract int getLayoutRes();

    protected Unbinder bind;

    protected AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((MyApp) getApplication()).appComponent;
        setupActivityComponent(appComponent);
    }

    protected abstract void onCreated();

    /**
     * todo 暂时兼容 后期 会修改：Fragment-> BaseMavpFragment
     *
     * @param id
     * @param canBack
     * @param fragments
     */
    protected void addFragmentAndHidden(int id, boolean canBack, /*BaseMvp*/Fragment... fragments) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (/*BaseMvp*/Fragment fragment : fragments) {
            fragmentTransaction.add(id, fragment).hide(fragment);
            this.fragments.add(fragment);
            if (canBack) {
                fragmentTransaction.addToBackStack(null);
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * todo 暂时兼容 后期 会修改：Fragment-> BaseMavpFragment
     */
    protected void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (Fragment f : fragments) {
            if (f.equals(fragment)) {
                fragmentTransaction.show(f);
            } else {
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commit();

    }

    public void setLayout() {
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        if (App.app().getUserInfo() != null)
            WaterMarkUtils.addWaterMark(this, App.app().getUserInfo().getUserInfo().getName() + " " + App.app().getUserInfo().getUserInfo().getCode(), 270 + 45, getResources().getColor(R.color.wt), 60);
    }

    protected AlertDialog showSuccess(View view) {

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_success)
                .setCancelable(true)
                .create();
        dialog.show();
        view.postDelayed(() -> {
            dialog.dismiss();
            finish();
        }, 3000);
        return dialog;
    }

    protected AlertDialog showSuccess(View view, CallbackDialog callback) {

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_success)
                .setCancelable(true)
                .create();
        dialog.show();
        view.postDelayed(() -> {
            dialog.dismiss();
            callback.callback();
        }, 3000);
        return dialog;
    }

    protected AlertDialog showFailed(final CallbackDialog dialogCallback) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("提交失败")
                .setPositiveButton("重试", (dialog1, which) -> dialogCallback.callback())
                .setNegativeButton("取消", (dialog12, which) -> dialog12.dismiss()).create();
        dialog.show();
        return dialog;
    }

}
