package com.zhhl.concern.mvp.view.adapter;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.mvp.model.entity.LeadPushData;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2018/9/6.
 */
public class LeadPushAdapter extends BaseAdapter<LeadPushData, LeadPushAdapter.LeadPushVH> {


    public LeadPushAdapter(ArrayList<LeadPushData> data) {
        super(data);
    }

    @Override
    public LeadPushVH create(View view) {
        return new LeadPushVH(view);
    }

    @Override
    protected void bindView(LeadPushVH vh, int position, LeadPushData item) {
        if (position == 0) {
            vh.isNew.setVisibility(View.VISIBLE);
            vh.isNewIcon.setVisibility(View.VISIBLE);
        } else {
            vh.isNew.setVisibility(View.GONE);
            vh.isNewIcon.setVisibility(View.GONE);
        }
        vh.mContent.setText(item.getContent());
        vh.mTitle.setText(item.getTitle());
        vh.mTime.setText(DateFormat.format("MM-dd HH:mm", item.getTime()));
    }


    public void add(String data, long time) {
        this.data.add(new LeadPushData(data,time));
        notifyDataSetChanged();
    }

    @Override
    protected int getLayout() {
        return R.layout.item_lead_push;
    }

    static class LeadPushVH extends BaseAdapter.ViewHolder {
        @BindView(R.id.isNewIcon)
        ImageView isNewIcon;
        @BindView(R.id.isNew)
        TextView isNew;
        @BindView(R.id.mTitle)
        TextView mTitle;
        @BindView(R.id.mContent)
        TextView mContent;
        @BindView(R.id.mTime)
        TextView mTime;


        LeadPushVH(View view) {
            super(view);
        }
    }
}

