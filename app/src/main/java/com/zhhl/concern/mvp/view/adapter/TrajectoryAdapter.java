package com.zhhl.concern.mvp.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhhl.concern.R;
import com.zhhl.concern.common.adapter.BaseAdapter;
import com.zhhl.concern.tcp.data.PersonTrajectory;
import com.zhhl.concern.util.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by miao on 2018/9/5.
 */
public class TrajectoryAdapter extends BaseAdapter<PersonTrajectory, TrajectoryAdapter.TrajectoryViewHolder> {
    public TrajectoryAdapter(ArrayList<PersonTrajectory> data) {
        super(data);
    }

    @Override
    public TrajectoryViewHolder create(View view) {
        return new TrajectoryViewHolder(view);
    }

    @Override
    protected void bindView(TrajectoryViewHolder vh, int position, PersonTrajectory item) {

        switch (item.getType()) {
            case 1:
                vh.mTrajectoryDate.setText(DateUtil.format("yyyy-MM-dd", DateUtil.parseDate("yyyyMMdd", item.getHouse().getCsrq())));
                vh.mTrajectoryType.setText("住址:");
                vh.mTrajectoryTypeName.setText(item.getHouse().getHjxz());
                vh.mTrajectoryLocation.setText(item.getHouse().getZzxz());
                vh.mTrajectoryTime.setText(DateUtil.format("yyyy-MM-dd", DateUtil.parseDate("yyyyMMdd", item.getHouse().getCsrq())));
                vh.mTrajectoryIcon.setImageResource(getRes("酒店名称"));
                break;
            case 2:
                vh.mTrajectoryDate.setText(DateUtil.format("yyyy-MM-dd", DateUtil.parseDate("yyyy-MM-dd HH:mm", item.getUnLock().getCreateDate())));
                vh.mTrajectoryType.setText("开锁:");
                vh.mTrajectoryTypeName.setText(item.getUnLock().getDeptName());
                vh.mTrajectoryLocation.setText(item.getUnLock().getAddress() + " " + item.getUnLock().getDetailAddress());
                vh.mTrajectoryTime.setText(item.getUnLock().getCreateDate());
                vh.mTrajectoryIcon.setImageResource(getRes("酒店名称"));
                break;
            case 3:
                vh.mTrajectoryDate.setText(DateUtil.format("yyyy-MM-dd", DateUtil.parseDate("yyyy-MM-dd HH:mm", item.getSzqy().getCreateDate())));
                vh.mTrajectoryType.setText("散装汽油:");
                vh.mTrajectoryTypeName.setText(item.getSzqy().getGasolineUse());
                vh.mTrajectoryLocation.setText(item.getSzqy().getAddress());
                vh.mTrajectoryTime.setText(item.getSzqy().getCreateDate());
                vh.mTrajectoryIcon.setImageResource(getRes("酒店名称"));
                break;
            case 4:
                vh.mTrajectoryDate.setText(DateUtil.format("yyyy-MM-dd", DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", item.getNoId().getCreateDate())));
                vh.mTrajectoryType.setText("无证居住:");
                vh.mTrajectoryTypeName.setText(/*item.getNoId().getResult()*/"");
                vh.mTrajectoryLocation.setText(item.getNoId().getAddress());
                vh.mTrajectoryTime.setText(item.getNoId().getCreateDate());
                vh.mTrajectoryIcon.setImageResource(getRes("酒店名称"));
                break;

        }
        if (position == 0) {
            vh.mTrajectoryIsNew.setVisibility(View.VISIBLE);
            vh.mTrajectoryLineTop.setVisibility(View.INVISIBLE);
            vh.mTrajectoryLineBottom.setVisibility(View.VISIBLE);
        } else if (position + 1 == getCount()) {
            vh.mTrajectoryIsNew.setVisibility(View.INVISIBLE);
            vh.mTrajectoryLineTop.setVisibility(View.VISIBLE);
            vh.mTrajectoryLineBottom.setVisibility(View.INVISIBLE);

        } else {
            vh.mTrajectoryIsNew.setVisibility(View.INVISIBLE);
            vh.mTrajectoryLineBottom.setVisibility(View.VISIBLE);
            vh.mTrajectoryLineTop.setVisibility(View.VISIBLE);
        }

        if (getCount() == 1) {
            vh.mTrajectoryIsNew.setVisibility(View.VISIBLE);
            vh.mTrajectoryLineTop.setVisibility(View.INVISIBLE);
            vh.mTrajectoryLineBottom.setVisibility(View.INVISIBLE);
        }
    }

    public void addDate(List<PersonTrajectory> list) {
        data.addAll(list);
//        Log.e( "addDate: ",  Arrays.toString(data.toArray()));
        Collections.sort(data);
        Log.e("addDate: ", Arrays.toString(data.toArray()));
        notifyDataSetChanged();
    }

    //1533207600000
    //1533208680000
    @Override
    protected int getLayout() {
        return R.layout.item_trajectory;
    }

    private int getRes(String type) {
        switch (type) {
            case "火车":
                return R.mipmap.train;
            case "酒店名称":
                return R.mipmap.hotel;
            case "卡口过车":
                return R.mipmap.camera;
            case "航空":
                return R.mipmap.airport;
            default:
                return 0;
        }


    }

//    public void add(List<PersonTrajectoryHouse.ObjBean> obj) {
//        carTrajectBayonet.clear();
//        carTrajectBayonet.addAll(obj);
//        notifyDataSetChanged();
//
//    }

    static class TrajectoryViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.mTrajectoryDate)
        TextView mTrajectoryDate;
        @BindView(R.id.mTrajectoryLineTop)
        View mTrajectoryLineTop;
        @BindView(R.id.mTrajectoryIcon)
        ImageView mTrajectoryIcon;
        @BindView(R.id.mTrajectoryLineBottom)
        View mTrajectoryLineBottom;
        @BindView(R.id.mTrajectoryType)
        TextView mTrajectoryType;
        @BindView(R.id.mTrajectoryTypeName)
        TextView mTrajectoryTypeName;
        @BindView(R.id.mTrahectoryIsNew)
        TextView mTrajectoryIsNew;
        @BindView(R.id.mTrajectoryLocation)
        TextView mTrajectoryLocation;
        @BindView(R.id.mTrajectoryTime)
        TextView mTrajectoryTime;

        TrajectoryViewHolder(View view) {
            super(view);
        }
    }

}

