package com.lgy.drive.view.popu;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.lgy.drive.R;
import com.lgy.drive.bean.CityBean;
import com.lgy.drive.listence.SelectCityListence;
import com.lgy.drive.ui.adapter.SelectCityAdapter;
import com.lgy.drive.view.popu.base.BasePopupWindow;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${lgy} on 2018/4/308:52
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class SelectcityPop extends BasePopupWindow implements MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    @BindView(R.id.lv_pop)
    LinearLayout lvPop;
    private int code;

    private SelectCityListence selectCityListence;

    private List<CityBean> cityBeans = new ArrayList<>();
    ;

    SelectCityAdapter selectCityAdapter;

    public SelectcityPop(Activity context, int code, SelectCityListence selectCityListence) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this, mPopupView);
        this.code = code;
        this.selectCityListence = selectCityListence;
        initView();
        initData();
    }

    public void initView() {
        rvCity.setLayoutManager(new LinearLayoutManager(mContext));
        selectCityAdapter = new SelectCityAdapter(mContext, R.layout.list_pop_select_city, cityBeans);
        selectCityAdapter.setOnItemClickListener(this);
        rvCity.setAdapter(selectCityAdapter);
    }

    public void initData() {

        cityBeans.add(new CityBean("122", "鹿邑"));
        cityBeans.add(new CityBean("123", "郑州"));
        selectCityAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreatePopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.pop_select_city, null);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return lvPop;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        selectCityListence.selectCitySuccess(cityBeans.get(position), code);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
