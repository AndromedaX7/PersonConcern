package com.zhhl.concern.mvp.view.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.tcp.data.QueryApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2018/9/5.
 */
public class PendingApprovalAdapter extends BaseAdapter<QueryApprove.ObjBean, PendingApprovalAdapter.PendingApprovalViewHolder> {
    public PendingApprovalAdapter(ArrayList<QueryApprove.ObjBean> data) {
        super(data);
    }


    @Override
    public PendingApprovalViewHolder create(View view) {
        return new PendingApprovalViewHolder(view);
    }

    @Override
    protected void bindView(PendingApprovalViewHolder pendingApprovalViewHolder, int position, QueryApprove.ObjBean item) {
        pendingApprovalViewHolder.mTitle.setText(item.getTitle());
        if (bundle != null) {
            ViewApprove.ObjBean objBean = bundle.getParcelable(item.getRequestid());
            if (objBean != null) {
                pendingApprovalViewHolder.mItemContent.setText(objBean.getApplyContent());

                Log.e(">>>bindView: ", objBean.getApproveFlag());
                boolean flag;
                String state;
                if (objBean.getApproveFlag().equals("0")) {
                    flag = true;
                    state = "待审批";
                } else {
                    flag = false;
                    state = "已审批";
                }
                pendingApprovalViewHolder.mApprovalState.setBackgroundColor(context.getResources().getColor(flag ? R.color.colorApprovalFailed : R.color.colorBlueTextBar));
                pendingApprovalViewHolder.mApprovalState.setText(state);
            }
        }

//        pendingApprovalViewHolder.mItemConcernUserName.setText("【" + item.getConcernUserName() + "】");
//
        pendingApprovalViewHolder.mItemPetitioner.setText(item.getApplyPerson());
        pendingApprovalViewHolder.mItemPetitionDate.setText(item.getCreateDate());


    }

    @Override
    protected int getLayout() {
        return R.layout.item_pending_approval;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }

    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    static class PendingApprovalViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.mItemConcernUserName)
        TextView mItemConcernUserName;
        @BindView(R.id.mItemContent)
        TextView mItemContent;
        @BindView(R.id.mItemPetitioner)
        TextView mItemPetitioner;
        @BindView(R.id.mItemPetitionDate)
        TextView mItemPetitionDate;
        @BindView(R.id.mApprovalState)
        TextView mApprovalState;
        @BindView(R.id.mTitle)
        TextView mTitle;

        PendingApprovalViewHolder(View view) {
            super(view);
        }
    }
}


