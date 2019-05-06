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
import com.zhhl.concern.mvp.view.activities.TrajectoryAnalysis;
import com.zhhl.concern.mvp.view.adapter.PendingApprovalAdapter;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.util.DialogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ApprovalFragment extends Fragment {

    @BindView(R.id.mPendingApprovalList)
    ListView mPendingApprovalList;
    private PendingApprovalAdapter adapter = new PendingApprovalAdapter(new ArrayList<>());

    public static ApprovalFragment newInstance() {
        return new ApprovalFragment();
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
        mPendingApprovalList.setAdapter(adapter);

    }

    @OnItemClick(R.id.mPendingApprovalList)
    void onItemPress(int position) {
        QueryApprove.ObjBean pendingApprovalData = ((PendingApprovalAdapter) mPendingApprovalList.getAdapter()).getData().get(position);
        approval.putExtra("approval", pendingApprovalData);
        startActivity(approval);
    }

    private Intent approval = new Intent(TrajectoryAnalysis.ACTION);

    public void setData(ArrayList<QueryApprove.ObjBean> approval) {
        adapter.setData(approval);
    }

    public void putBundle(Bundle bundle) {
        adapter.setBundle(bundle);
    }
}
