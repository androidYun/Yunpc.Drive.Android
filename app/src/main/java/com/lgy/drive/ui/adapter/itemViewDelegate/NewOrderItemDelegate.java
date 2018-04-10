package com.lgy.drive.ui.adapter.itemViewDelegate;

import android.view.View;

import com.lgy.drive.R;
import com.lgy.drive.enumbean.OrderStateEnum;
import com.lgy.drive.model.http.resp.OrderBean;
import com.lgy.drive.utils.TimeUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by ${lgy} on 2018/4/814:58
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class NewOrderItemDelegate implements ItemViewDelegate<OrderBean> {
    private MultiItemTypeAdapter.OnItemClickListener mOnItemClickListener;


    public NewOrderItemDelegate(MultiItemTypeAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.list_new_order;
    }

    @Override
    public boolean isForViewType(OrderBean item, int position) {
        if (item.orderstate == OrderStateEnum.NEW_ORDER.getCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void convert(final ViewHolder holder, OrderBean orderBean, final int position) {
        OrderBean.RecordBean record = orderBean.record;
        holder.setText(R.id.tv_start_place, record.startplace + "");
        holder.setText(R.id.tv_end_place, record.arriveplace + "");
        holder.setText(R.id.tv_time, TimeUtils.getTime(record.starttime, TimeUtils.pattern_ymd_h));
        holder.setText(R.id.tv_takenumber, "乘坐人数" + orderBean.takenumber + "");
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
