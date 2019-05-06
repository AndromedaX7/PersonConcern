package com.zhhl.concern.mvp.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.tcp.data.MyApprove;
import com.zhhl.concern.tcp.data.ViewApprove;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;


public class TrajectoryListAdapter extends BaseAdapter<MyApprove.ObjBean, TrajectoryListAdapter.TrajectoryListViewHolder> {


    private HashMap<String, ViewApprove.ObjBean> map = new HashMap<>();

    public TrajectoryListAdapter(ArrayList<MyApprove.ObjBean> data) {
        super(data);
    }


    @Override
    public TrajectoryListViewHolder create(View view) {
        return new TrajectoryListViewHolder(view);
    }

    @Override
    protected void bindView(TrajectoryListViewHolder vh, int position, MyApprove.ObjBean item) {
        ViewApprove.ObjBean objBean = map.get(item.getRequestid());
        if (objBean != null) {
            vh.mConcernContent.setText(objBean.getApplyContent());
            vh.mConcernUserName.setText("【" + objBean.getName() + "】");
            vh.mApprovalState.setVisibility(View.GONE);
            vh.stateTips.setVisibility(View.GONE);
            vh.mConcernTimeRange.setText(objBean.getAttentionFormdate() + "至" + objBean.getAttentionTodate());

//            vh.mConcernContent.setText("content");
//            vh.mConcernUserName.setText("  Name() ");
//            vh.mApprovalState.setVisibility(View.GONE);
//            vh.stateTips.setVisibility(View.GONE);
//            vh.mConcernTimeRange.setText( " a 至 b ");
        }
    }

    /**
     * Test
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (context == null) context = parent.getContext();
        TrajectoryListViewHolder vh;
        if (convertView == null) {
            convertView = getLayout(parent);
            vh = create(convertView);
            convertView.setTag(vh);
        } else {
            vh = (TrajectoryListViewHolder) convertView.getTag();
        }
        bindView(vh, position,  getItem(position));
        return convertView;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_people_concern;
    }

    static class TrajectoryListViewHolder extends BaseAdapter.ViewHolder {
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

        TrajectoryListViewHolder(View view) {
            super(view);
        }
    }

    public ViewApprove.ObjBean getMapItem(String key) {
        return map.get(key);
    }

    public void put(String key, ViewApprove.ObjBean o) {
        map.put(key, o);
        notifyDataSetChanged();
    }
}


