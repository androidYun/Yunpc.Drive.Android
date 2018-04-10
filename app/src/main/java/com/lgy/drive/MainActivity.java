package com.lgy.drive;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lgy.drive.PresneterView.MainView;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.common.Constants;
import com.lgy.drive.enumbean.OrderStateEnum;
import com.lgy.drive.model.http.resp.AppointmentCarResp;
import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.model.http.resp.HandlerOrderResp;
import com.lgy.drive.model.http.resp.OrderBean;
import com.lgy.drive.model.http.resp.OrderResp;
import com.lgy.drive.model.http.resp.UploadResp;
import com.lgy.drive.persenter.MainPresenter;
import com.lgy.drive.ui.activity.AddRecordActivity;
import com.lgy.drive.ui.activity.OrderActivity;
import com.lgy.drive.ui.activity.RegisterActivity;
import com.lgy.drive.ui.adapter.OrderAdapter;
import com.lgy.drive.utils.ToastUtil;
import com.lgy.drive.utils.imageutils.ImageLoaderProxy;
import com.lgy.drive.utils.sp.UserSpUtils;
import com.lgy.drive.view.CircleImageView;
import com.lgy.drive.view.NaviTitleView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView, MultiItemTypeAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.navi_view)
    NaviTitleView naviView;
    @BindView(R.id.rv_order)
    XRecyclerView rvOrder;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_add_record)
    TextView tvAddRecord;
    @BindView(R.id.lv_drawer)
    LinearLayout lvDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    OrderAdapter orderAdapter;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_set)
    TextView tvSet;

    private List<OrderBean> orderBeanList;

    private int pageNumber = 1;
    private int totalPageCount;


    @Override
    protected void initView() {
        super.initView();
        orderBeanList = new ArrayList<>();
        orderAdapter = new OrderAdapter(this, R.layout.list_new_order, orderBeanList);
        orderAdapter.setOnItemClickListener(this);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(orderAdapter);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        naviView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        naviView.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果抽屉关闭就打开，如果抽屉打开就关闭
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {//如果已经是关闭状态
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        rvOrder.setPullRefreshEnabled(true);
        rvOrder.setLoadingMoreEnabled(true);
        rvOrder.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNumber = 1;
                mPresenter.getOrderList(OrderStateEnum.NEW_ORDER.getCode(), pageNumber, Constants.PAGE_SIZE);
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
        DriveBean driveBean = UserSpUtils.getDriveBean();
        if (driveBean != null) {
            ImageLoaderProxy.getInstace().displayImage(Constants.BASE_URL + driveBean.drivehead, ivHead);
            tvName.setText(driveBean.name);
        }
        mPresenter.getOrderList(OrderStateEnum.NEW_ORDER.getCode(), pageNumber, Constants.PAGE_SIZE);
    }

    @Override
    public void onAppointmentSuccess(AppointmentCarResp appointmentCarResp) {

    }

    @Override
    public void getOrderSuccess(OrderResp orderResp) {
        totalPageCount = orderResp.data.pageInfo.totalPageCount;
        orderBeanList.clear();
        orderBeanList.addAll(orderResp.data.dataList);
        orderAdapter.notifyDataSetChanged();
        rvOrder.loadMoreComplete();
        rvOrder.refreshComplete();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        rvOrder.loadMoreComplete();
        rvOrder.refreshComplete();
    }

    @Override
    public void uploadImages(UploadResp uploadResp) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main_mian;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.tv_name, R.id.tv_add_record, R.id.tv_all_order, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.tv_add_record:
                startActivity(new Intent(this, AddRecordActivity.class));
                break;
            case R.id.tv_all_order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.tv_set:
                break;
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        switch (view.getId()) {
            case R.id.btn_call:
                applyForpermission(CALL_PHONE_CODE);
                break;
            case R.id.btn_accept:
                mPresenter.handlerOrder(OrderStateEnum.ACCEPT_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;
            case R.id.btn_cancel:
                mPresenter.handlerOrder(OrderStateEnum.REFUSE_ORDER.getCode(), orderBeanList.get(position).orderid);
                break;

        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    protected void onPermissionSuccess(int code) {
        super.onPermissionSuccess(code);
    }

    @Override
    public void handlerSuccess(HandlerOrderResp handlerOrderResp) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
