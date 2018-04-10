package com.lgy.drive.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lgy.drive.PresneterView.OrderView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseFragment;
import com.lgy.drive.common.Constants;
import com.lgy.drive.enumbean.OrderStateEnum;
import com.lgy.drive.model.http.resp.OrderBean;
import com.lgy.drive.model.http.resp.OrderResp;
import com.lgy.drive.persenter.OrderPresenter;
import com.lgy.drive.ui.adapter.itemViewDelegate.AcceptOrderItemDelegate;
import com.lgy.drive.ui.adapter.itemViewDelegate.FinishOrderItemDelegate;
import com.lgy.drive.ui.adapter.itemViewDelegate.NewOrderItemDelegate;
import com.lgy.drive.ui.adapter.itemViewDelegate.RefuseOrderItemDelegate;
import com.lgy.drive.utils.ToastUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by ${lgy} on 2018/4/418:43
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class AllOrderFragment extends BaseFragment implements OrderView, MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.rv_order)
    XRecyclerView rvOrder;
    Unbinder unbinder;
    private MultiItemTypeAdapter orderAdapter;

    private List<OrderBean> orderBeanList;

    private OrderPresenter orderPresenter;

    private int handlerOrderPosition;

    private int orderCode;

    private int pageNumber = 1;

    private int totalPageCount = 1;
    private OrderStateEnum orderStateEnum;


    public static AllOrderFragment getInstance(OrderStateEnum orderStateEnum) {
        AllOrderFragment allOrderFragment = new AllOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(OrderStateEnum.class.getName(), orderStateEnum);
        allOrderFragment.setArguments(bundle);
        return allOrderFragment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        orderBeanList = new ArrayList<>();
        orderAdapter = new MultiItemTypeAdapter(mContext, orderBeanList);
        orderAdapter.setOnItemClickListener(this);
        orderAdapter.addItemViewDelegate(new NewOrderItemDelegate(this));
        orderAdapter.addItemViewDelegate(new AcceptOrderItemDelegate(this));
        orderAdapter.addItemViewDelegate(new RefuseOrderItemDelegate(this));
        orderAdapter.addItemViewDelegate(new FinishOrderItemDelegate(this));
        rvOrder.setLayoutManager(new LinearLayoutManager(mContext));
        rvOrder.setAdapter(orderAdapter);
        rvOrder.setPullRefreshEnabled(true);
        rvOrder.setLoadingMoreEnabled(true);
        rvOrder.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNumber = 1;
                orderPresenter.getOrderList(orderStateEnum.getCode(), pageNumber, Constants.PAGE_SIZE);
            }

            @Override
            public void onLoadMore() {
                pageNumber++;
                if (totalPageCount <= pageNumber) {
                    ToastUtil.show(mContext, "没有更多数据");
                    rvOrder.loadMoreComplete();
                }
            }
        });
    }


    @Override
    protected void initData() {
        super.initData();
        orderPresenter = new OrderPresenter(this);
        Bundle arguments = getArguments();
        orderStateEnum = (OrderStateEnum) arguments.getSerializable(OrderStateEnum.class.getName());
        orderPresenter.getOrderList(orderStateEnum.getCode(), pageNumber, Constants.PAGE_SIZE);
    }

    @Override
    public void getOrderSuccess(OrderResp orderResp) {
        totalPageCount = orderResp.data.pageInfo.totalPageCount;
        rvOrder.refreshComplete();
        orderBeanList.clear();
        orderBeanList.addAll(orderResp.data.dataList);
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        rvOrder.refreshComplete();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        handlerOrderPosition = position;
        switch (view.getId()) {
            case R.id.btn_accept_call:

                break;
            case R.id.btn_accept_finish:
                orderCode = OrderStateEnum.SUCCESS_ORDER.getCode();
                orderPresenter.handlerOrder(OrderStateEnum.SUCCESS_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;
            case R.id.btn_finish_delete:

                break;
            case R.id.btn_call:

                break;
            case R.id.btn_cancel:
                orderCode = OrderStateEnum.REFUSE_ORDER.getCode();
                orderPresenter.handlerOrder(OrderStateEnum.REFUSE_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;
            case R.id.btn_accept:
                orderCode = OrderStateEnum.NEW_ORDER.getCode();
                orderPresenter.handlerOrder(OrderStateEnum.NEW_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;

            case R.id.btn_refuse_delete:
                orderCode = OrderStateEnum.REFUSE_ORDER.getCode();
                orderPresenter.handlerOrder(OrderStateEnum.REFUSE_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;

        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    public void handlerSucdess() {
        orderBeanList.get(handlerOrderPosition).orderstate = orderCode;
        orderAdapter.notifyDataSetChanged();
    }
}
