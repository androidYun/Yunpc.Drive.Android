package com.lgy.drive.ui.adapter;

import android.content.Context;

import com.lgy.drive.R;
import com.lgy.drive.bean.CityBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ${lgy} on 2018/4/309:06
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class SelectCityAdapter extends CommonAdapter<CityBean> {

    public SelectCityAdapter(Context context, int layoutId, List<CityBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CityBean cityBean, int position) {
        holder.setText(R.id.tv_city, cityBean.getCityName());
    }
}
