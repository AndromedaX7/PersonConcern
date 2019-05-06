package com.zhhl.concern.mvp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.tcp.data.MyApproveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by miao on 2018/9/5.
 */
public class PeopleConcernAdapter extends BaseAdapter<MyApproveData, PeopleConcernAdapter.PeopleConcernDataViewHolder> {

    private static final long threeDayLong = 3 * 24 * 60 * 60 * 1000;

    public PeopleConcernAdapter(ArrayList<MyApproveData> data) {
        super(data);
    }

    @Override
    public PeopleConcernDataViewHolder create(View view) {
        return new PeopleConcernDataViewHolder(view);
    }

    @Override
    protected void bindView(PeopleConcernDataViewHolder vh, int position, MyApproveData item) {
        vh.mConcernContent.setText(item.getViewApprove().getApplyContent());
        vh.mConcernUserName.setText("【" + item.getViewApprove().getName() + "】");
        vh.mApprovalState.setText(getApprovalState(item.getMyApprove().getOverFlag()));
        vh.mApprovalState.setBackgroundColor(context.getResources().getColor(item.getMyApprove().getOverFlag().equals("1") ? R.color.colorBlueTextBar : R.color.colorApprovalFailed));
        String dateRange = item.getViewApprove().getAttentionFormdate() + "至" + item.getViewApprove().getAttentionTodate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        try {
            Date endDate = dateFormat.parse(item.getViewApprove().getAttentionTodate());
            if (endDate.getTime() - threeDayLong <= System.currentTimeMillis()) {

                vh.stateTips.setVisibility(View.VISIBLE);
                vh.mConcernTimeRange.setText(dateRange + " 即将超期关闭权限");
                vh.mConcernTimeRange.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
            } else {
                vh.stateTips.setVisibility(View.GONE);
                vh.mConcernTimeRange.setText(dateRange);
                vh.mConcernTimeRange.setTextColor(context.getResources().getColor(R.color.colorTextDefault));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getApprovalState(String overFlag) {
        switch (overFlag) {
            case "0":
                return "未审批";
            case "1":
                return "已审批";
            case "2":
                return "未通过";

        }
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_people_concern;
    }

    static class PeopleConcernDataViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.mConcernUserName)
        TextView mConcernUserName;
        @BindView(R.id.stateTips)
        TextView stateTips;
        @BindView(R.id.mConcernContent)
        TextView mConcernContent;
        @BindView(R.id.mConcernTimeRange)
        TextView mConcernTimeRange;
        @BindView(R.id.mApprovalState)
        TextView mApprovalState;

        PeopleConcernDataViewHolder(View view) {
            super(view);
        }
    }

}

