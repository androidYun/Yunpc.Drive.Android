package com.lgy.drive.ui.activity;


import com.lgy.drive.PresneterView.PinCarListView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.model.http.resp.PinCarListResp;
import com.lgy.drive.persenter.PinCarListPresenter;

/**
 * Created by ${lgy} on 2018/3/1509:36
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class PinCarDetailActivity extends BaseMvpActivity<PinCarListPresenter> implements PinCarListView {



    @Override
    protected int getLayout() {
        return R.layout.activity_pincar_detail;
    }

    @Override
    public void showPinCarList(PinCarListResp pinCarListResp) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
