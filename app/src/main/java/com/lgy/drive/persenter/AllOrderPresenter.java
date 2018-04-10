package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.AllOrderView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.AllOrderResp;
import com.lgy.drive.model.net.DefineSubscriber;
import com.lgy.drive.model.net.RetrofitHelper;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${lgy} on 2018/3/1211:46
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class AllOrderPresenter extends RxPresenter<AllOrderView> {

    @Inject
    public AllOrderPresenter() {
    }

    public void doAllOrder(int userId, int pageNow, int pageSize) {
        Subscription subscribe = RetrofitHelper.getUserApi().getAllOrder(4, pageNow, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<AllOrderResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(AllOrderResp allOrderResp) {
                        mView.onAllOrderSuccess(allOrderResp);
                    }
                });
        addSubscribe(subscribe);
    }
}
