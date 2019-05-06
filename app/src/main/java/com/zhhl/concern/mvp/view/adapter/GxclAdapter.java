package com.zhhl.concern.mvp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.common.adapter.BaseAdapter_NR;
import com.zhhl.concern.tcp.data.GxclData;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2019/1/24.
 */
public class GxclAdapter extends BaseAdapter<GxclData.QsclBean, GxclAdapter.GxclViewHolder> {
    public GxclAdapter(ArrayList<GxclData.QsclBean> data) {
        super(data);
    }

    @Override
    protected GxclViewHolder create(View view) {
        return new GxclViewHolder(view);
    }

    @Override
    protected void bindView(GxclViewHolder vh, int position, GxclData.QsclBean item) {
        vh.carCode.setText(item.getJdchphm());
        vh.name.setText(item.getJdcsyrJdcsyrmc());
    }

    @Override
    protected int getLayout() {
        return R.layout.gxcl_trajectory;
    }

    static class GxclViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.carCode)
        TextView carCode;
        @BindView(R.id.name)
        TextView name;
        public GxclViewHolder(View view) {
            super(view);
        }
    }

}
