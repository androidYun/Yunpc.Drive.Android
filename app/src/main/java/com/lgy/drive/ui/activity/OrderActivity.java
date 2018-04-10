package com.lgy.drive.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lgy.drive.R;
import com.lgy.drive.base.BaseActivity;
import com.lgy.drive.enumbean.OrderStateEnum;
import com.lgy.drive.ui.fragment.AllOrderFragment;
import com.lgy.drive.view.NaviTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ${lgy} on 2018/4/418:40
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class OrderActivity extends BaseActivity {

    @BindView(R.id.navi_view)
    NaviTitleView naviView;
    @BindView(R.id.tab_title)
    TabLayout tabTitle;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    OrderHeadVpAdapter orderHeadVpAdapter;

    private List<OrderStateEnum> orderStateEnumsList;

    private List<String> mTabNameList;

    @Override
    protected void initView() {
        super.initView();
        mTabNameList = new ArrayList<>();
        mTabNameList.add("全部订单");
        mTabNameList.add("新订单");
        mTabNameList.add("接受订单");
        mTabNameList.add("拒绝订单");
        mTabNameList.add("已完成订单");
        orderStateEnumsList = new ArrayList<>();
        orderStateEnumsList.add(OrderStateEnum.ALL_ORDER);
        orderStateEnumsList.add(OrderStateEnum.NEW_ORDER);
        orderStateEnumsList.add(OrderStateEnum.ACCEPT_ORDER);
        orderStateEnumsList.add(OrderStateEnum.REFUSE_ORDER);
        orderStateEnumsList.add(OrderStateEnum.SUCCESS_ORDER);
        orderHeadVpAdapter = new OrderHeadVpAdapter(getSupportFragmentManager());
        vpOrder.setAdapter(orderHeadVpAdapter);
        tabTitle.setupWithViewPager(vpOrder);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    public class OrderHeadVpAdapter extends FragmentPagerAdapter {

        public OrderHeadVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AllOrderFragment.getInstance(orderStateEnumsList.get(position));
        }

        @Override
        public int getCount() {
            return orderStateEnumsList.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNameList.get(position);
        }
    }

}
