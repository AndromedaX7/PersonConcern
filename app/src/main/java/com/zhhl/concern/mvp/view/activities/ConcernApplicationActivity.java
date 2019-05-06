package com.zhhl.concern.mvp.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zhhl.concern.R;
import com.zhhl.concern.app.MyApp;
import com.zhhl.concern.common.activity.BaseMvpActivity;
import com.zhhl.concern.di.component.AppComponent;
import com.zhhl.concern.di.component.DaggerConcernApplicationComponent;
import com.zhhl.concern.di.module.ConcernApplicationModule;
import com.zhhl.concern.mvp.contract.ConcernApplicationContract;
import com.zhhl.concern.mvp.presenter.ConcernApplicationPresenter;
import com.zhhl.concern.mvp.view.adapter.DateSelectAdapter;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConcernApplicationActivity extends BaseMvpActivity<ConcernApplicationPresenter> implements ConcernApplicationContract.View {

    public static final String ACTION = "com.zhhl.activity.ConcernRequest";

    @BindView(R.id.mConcernPeopleNameTv)
    TextView mConcernPeopleNameTv;
    @BindView(R.id.mConcernPeopleNameEt)
    EditText mConcernPeopleNameEt;
    @BindView(R.id.mConcernPeopleIdCodeTv)
    TextView mConcernPeopleIdCodeTv;
    @BindView(R.id.mConcernPeopleIdCodeEt)
    EditText mConcernPeopleIdCodeEt;
    @BindView(R.id.mConcernTimeRangeTv)
    TextView mConcernTimeRangeTv;
    @BindView(R.id.mConcernTimeStart)
    Spinner mConcernTimeStart;
    @BindView(R.id.mConcernTimeEnd)
    Spinner mConcernTimeEnd;
    @BindView(R.id.mRequestTv)
    TextView mRequestTv;
    @BindView(R.id.mRequestEt)
    EditText mRequestEt;
    @BindView(R.id.commit)
    TextView commit;
    ProgressDialog progressDialog;
    private DateSelectAdapter dateStartAdapter;
    private DateSelectAdapter dateEndAdapter;
    private String startDate;
    private String endDate;
    private String dataMessage = "";


    private String idCode;
    private String name;
    private String content;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerConcernApplicationComponent.builder()
                .appComponent(appComponent)
                .concernApplicationModule(new ConcernApplicationModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.concern_application;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        ButterKnife.bind(this);
        initAdapter();
        MyApprove.ObjBean data = getIntent().getParcelableExtra("carTrajectBayonet");
        ViewApprove.ObjBean view = getIntent().getParcelableExtra("view");
        if (data != null && view != null) {
            mConcernPeopleNameEt.setText(view.getName());
            mConcernPeopleIdCodeEt.setText(view.getIdNumber());
            mRequestEt.setText(view.getApplyContent());
        }
        mConcernPeopleNameTv.setText(Html.fromHtml("姓名<font color = red>*</>"));
        mConcernPeopleIdCodeTv.setText(Html.fromHtml("身份证号<font color = red>*</>"));
        mConcernTimeRangeTv.setText(Html.fromHtml("关注时限<font color = red>*</>"));
        mRequestTv.setText(Html.fromHtml("申请事由<font color = red>*</>"));
        mConcernTimeStart.setAdapter(dateStartAdapter);
        mConcernTimeEnd.setAdapter(dateEndAdapter);
        mConcernTimeStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + position);
                onTimeStartPress(position);
                startDate = dateStartAdapter.getData().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mConcernTimeEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                endDate = dateEndAdapter.getData().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initAdapter() {
        GregorianCalendar calendar = new GregorianCalendar();


        ArrayList<String> strings = generateData(calendar.get(Calendar.YEAR), 1, 0);
        dateStartAdapter = new DateSelectAdapter(strings);
        dateEndAdapter = new DateSelectAdapter(strings);
    }

    private ArrayList<String> generateData(int year, int offsetMonth, int dateOffset) {
        if (offsetMonth <= 0) offsetMonth = 1;
        ArrayList<String> data = new ArrayList<>();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int date = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        int month = gregorianCalendar.get(Calendar.MONTH);
        gregorianCalendar.set(Calendar.YEAR, year);
        String monthString;
        String dateString;
        for (int m = month; m <= month + offsetMonth + dateOffset; m++) {
            gregorianCalendar.set(Calendar.MONTH, m);
            if (m + 1 < 10)
                monthString = "0%d";
            else {
                monthString = "%d";
            }
            for (int i = 1; i <= gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                if (i < 10)
                    dateString = "0%d";
                else {
                    dateString = "%d";
                }

                System.out.printf("%d-%d\n", (m + 1) % 12 == 0 ? 12 : ((m + 1) % 12), i);
                data.add(String.format(Locale.CHINA, "%d-" + monthString + "-" + dateString, year, (m + 1) % 12 == 0 ? 12 : ((m + 1) % 12), i));
            }

        }

        for (int i = 1; i < date; i++) {
            data.remove(0);
        }

        for (String s :
                data) {
            System.out.println(s);
        }

        return data;
    }

    void onTimeStartPress(int id) {
        ArrayList<String> data = new ArrayList<>(dateStartAdapter.getData());
        for (int i = 0; i < id; i++) {
            data.remove(0);
        }
        dateEndAdapter.setData(data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreated();
    }

    @OnClick(R.id.commit)
    void onCommitPress() {
        //todo show dialog and finish

        boolean dataFlag = true;

        idCode = mConcernPeopleIdCodeEt.getText().toString();
        name = mConcernPeopleNameEt.getText().toString();
        content = mRequestEt.getText().toString();

        if (name.equals("")) {
            dataFlag = false;
            dataMessage = "姓名不能为空";
        }
        if (dataFlag && idCode.equals("")) {
            dataFlag = false;
            dataMessage = "身份证号不能为空";
        }
        if (dataFlag && content.equals("")) {
            dataFlag = false;
            dataMessage = "申请事由不能为空";
        }
        if (dataFlag && startDate == null || startDate.equals("")) {
            dataFlag = false;
            dataMessage = "请选择起始时间";
        }
        if (dataFlag && endDate == null || endDate.equals("")) {
            dataFlag = false;
            dataMessage = "请选择结束时间";
        }

        if (dataFlag) {
            if (progressDialog == null)
                progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在提交，请稍后...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //todo applyPerson ----------------------------
            mPresent.caseRecord(idCode);
        } else {
            Toast.makeText(this, dataMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void success() {
        progressDialog.dismiss();
        showSuccess(commit, () -> startActivity(new Intent(MainActivity.ACTION)));
    }

    @Override
    public void error() {
        progressDialog.dismiss();
        showFailed(this::onCommitPress);
    }

    @Override
    public void hasRecord() {
        mPresent.commitRequestFast(content, ((MyApp) (getApplication())).getCode(), idCode, name, startDate, endDate);
    }

    @Override
    public void noRecord() {
        mPresent.commitRequest(content, ((MyApp) (getApplication())).getCode(), idCode, name, startDate, endDate);
    }
}