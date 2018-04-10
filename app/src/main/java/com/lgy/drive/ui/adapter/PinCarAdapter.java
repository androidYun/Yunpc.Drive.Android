package com.lgy.drive.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lgy.drive.R;
import com.lgy.drive.model.http.resp.PinCarListResp;
import com.lgy.drive.utils.TimeUtils;
import com.lgy.drive.utils.imageutils.ImageLoaderProxy;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ${lgy} on 2018/3/1410:59
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class PinCarAdapter extends CommonAdapter<PinCarListResp.DataBean> {
    public PinCarAdapter(Context context, int layoutId, List<PinCarListResp.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(final ViewHolder holder, PinCarListResp.DataBean dataBean, final int position) {
        PinCarListResp.DataBean.DriveBean drive = dataBean.drive;
        if (drive == null) {
            return;
        }
        holder.setText(R.id.tv_name, drive.driveName);
        ImageLoaderProxy.getInstace().displayImage(drive.driveHead, (ImageView) holder.getView(R.id.iv_head));
        holder.setText(R.id.tv_drive_age, drive.driveName);
        holder.setText(R.id.tv_start_end_place, dataBean.startPlace + "---" + dataBean.arrivePlace);
        holder.setText(R.id.tv_start_end_time, TimeUtils.getDateToString(dataBean.startTime, TimeUtils.pattern_ymd_hms) + "---" + TimeUtils.getDateToString(dataBean.endTime, TimeUtils.pattern_ymd_hms));
        holder.setText(R.id.tv_content, dataBean.recordContent);
        holder.setOnClickListener(R.id.tv_callphone, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, holder, position);
            }
        });
        holder.setOnClickListener(R.id.tv_appointment, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, holder, position);
            }
        });
    }
}
