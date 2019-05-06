package com.zhhl.concern.mvp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2018/9/4.
 */
public class DateSelectAdapter extends BaseAdapter<String, DateSelectAdapter.DateSelectViewHolder> {

    public DateSelectAdapter(ArrayList<String> data) {
        super(data);
    }

    @Override
    public DateSelectViewHolder create(View view) {
        return new DateSelectViewHolder(view);
    }

    @Override
    protected void bindView(DateSelectViewHolder dateSelectViewHolder, int position, String item) {
        dateSelectViewHolder.textView.setText(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_spanner;
    }


    static class DateSelectViewHolder extends BaseAdapter.ViewHolder {

        @BindView(android.R.id.text1)
        TextView textView;

        DateSelectViewHolder(View view) {
            super(view);
        }
    }

}

