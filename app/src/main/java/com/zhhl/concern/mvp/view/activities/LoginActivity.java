package com.zhhl.concern.mvp.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerLoginComponent;
import com.zhhl.concern.di.module.LoginModule;
import com.zhhl.concern.mvp.contract.LoginContract;
import com.zhhl.concern.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by miao on 2018/9/7.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {


    public static final String ACTION = "com.zhhl.activity.Login";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mLogo)
    ImageView mLogo;
    @BindView(R.id.mLogin)
    TextView mLogin;

    ProgressDialog p;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;

    private SparseArray<String> map = new SparseArray<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this)) //请将LoginModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
//        mLogin.requestFocus();
        /*"17600194545"*//*"16643416141"*//*"15678779990"*/
        map.put(1, "17600194545");
        map.put(2, "16643416141");
        map.put(3, "15678779990");
        map.put(4, "206589");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }

    @OnClick(R.id.mLogin)
    void onLogin() {
        boolean flag = true;
        int idx = getCheckIndex();
        if (idx >= 1 && idx <= 3) {
            String password = map.get(idx);

            mPresent.login("", password);
        } else {
            mPresent.login(map.get(idx), "");
        }

//        String message = "";
//        String account = mAccount.getText().toString();
//        String password = mPassword.getText().toString();
//        if (account.equals("")) {
//            flag = false;
//            message = "请输入警号";
//        }
//        if (flag && password.equals("")) {
//            flag = false;
//            message = "请输入密码";
//        }
//        if (flag) {
//            if (p == null) {
//                p = new ProgressDialog(this);
//                p.setMessage("正在登陆");
//                p.setCancelable(false);
//            }
//            p.show();
//            mPresent.login(account, password);
//            mLogin.postDelayed(() -> {
//                p.dismiss();
//                AlertDialog ad = System.currentTimeMillis() % 2 == 0 ? showSuccess(mLogin, () -> {
//                    startActivity(new Intent(MainActivity.ACTION));
//                    finish();
//                }) : showFailed(this::onLogin);
//            }, 2000);
//        } else {
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        }


    }

    private int getCheckIndex() {
        int checkedRadioButtonId = rg.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.rb1:
                return 1;
            case R.id.rb2:
                return 2;
            case R.id.rb3:
                return 3;
            case R.id.rb4:
                return 4;
        }
        return 0;
    }

    @Override
    public void login() {
        startActivity(new Intent(MainActivity.ACTION));
    }

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}