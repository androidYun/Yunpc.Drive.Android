package com.lgy.drive.ui.activity;


import com.lgy.drive.PresneterView.AllOrderView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.model.http.resp.AllOrderResp;
import com.lgy.drive.persenter.AllOrderPresenter;
import com.lgy.drive.utils.sp.UserSpUtils;

/**
 * Created by ${lgy} on 2018/3/1611:18
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class OrderListActivity extends BaseMvpActivity<AllOrderPresenter> implements AllOrderView {


    @Override
    protected int getLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.doAllOrder(UserSpUtils.getUserId(), 1, 20);
    }

    @Override
    public void onAllOrderSuccess(AllOrderResp allOrderResp) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
