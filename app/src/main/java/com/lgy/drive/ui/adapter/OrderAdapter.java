package com.lgy.drive.ui.adapter;

import android.content.Context;
import android.view.View;

import com.lgy.drive.R;
import com.lgy.drive.model.http.resp.OrderBean;
import com.lgy.drive.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ${lgy} on 2018/4/416:38
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class OrderAdapter extends CommonAdapter<OrderBean> {

    public OrderAdapter(Context context, int layoutId, List<OrderBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(final ViewHolder holder, OrderBean orderBean, final int position) {
        OrderBean.RecordBean record = orderBean.record;
        holder.setText(R.id.tv_start_place, record.startplace+"");
        holder.setText(R.id.tv_end_place, record.arriveplace+"");
        holder.setText(R.id.tv_time, TimeUtils.getTime(record.starttime, TimeUtils.pattern_ymd_h));
        holder.setText(R.id.tv_takenumber, "乘坐人数"+orderBean.takenumber+"");
        holder.setOnClickListener(R.id.btn_call, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, holder, position);
            }
        });
        holder.setOnClickListener(R.id.btn_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, holder, position);
            }
        });
        holder.setOnClickListener(R.id.btn_accept, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, holder, position);
            }
        });
    }
}
