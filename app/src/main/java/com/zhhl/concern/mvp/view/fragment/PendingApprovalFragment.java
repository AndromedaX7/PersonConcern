package com.zhhl.concern.mvp.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhhl.concern.R;
import com.zhhl.concern.mvp.view.activities.ApprovalActivity;
import com.zhhl.concern.mvp.view.adapter.PendingApprovalAdapter;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;
import com.zhhl.concern.util.DialogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by miao on 2018/9/5.
 */

public class PendingApprovalFragment extends Fragment {

    @BindView(R.id.mPendingApprovalList)
    ListView mPendingApprovalList;

    ArrayList<QueryApprove.ObjBean> data = new ArrayList<>();

    private PendingApprovalAdapter pendingApprovalAdapter = new PendingApprovalAdapter(data);

    public static PendingApprovalFragment newInstance() {
        return new PendingApprovalFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_approval, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPendingApprovalList.setAdapter(pendingApprovalAdapter);


    }

    @OnItemClick(R.id.mPendingApprovalList)
    void onItemPress(int position) {
        QueryApprove.ObjBean pendingApprovalData = ((PendingApprovalAdapter) mPendingApprovalList.getAdapter()).getData().get(position);
        ViewApprove.ObjBean bundleItem = ((PendingApprovalAdapter) mPendingApprovalList.getAdapter()).getBundle().getParcelable(pendingApprovalData.getRequestid());
        approval.putExtra("approval", pendingApprovalData);
        approval.putExtra("bundleItem", bundleItem);
        startActivity(approval);
    }

    private Intent approval = new Intent(ApprovalActivity.ACTION);

    public void setData(ArrayList<QueryApprove.ObjBean> pendingApproval) {
        pendingApprovalAdapter.setData(pendingApproval);
    }

    public void putBundle(Bundle bundle) {
        pendingApprovalAdapter.setBundle(bundle);
    }
}