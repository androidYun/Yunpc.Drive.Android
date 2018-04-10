package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.PinCarListView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.PinCarListResp;
import com.lgy.drive.model.net.DefineSubscriber;
import com.lgy.drive.model.net.RetrofitHelper;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${lgy} on 2018/3/1315:05
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class PinCarListPresenter extends RxPresenter<PinCarListView> {
    @Inject
    public PinCarListPresenter() {
    }

    public void getPinCardList(int userId) {
        Subscription subscribe = RetrofitHelper.getUserApi().getPinCarList(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<PinCarListResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(PinCarListResp pinCarListResp) {
                        mView.showPinCarList(pinCarListResp);
                    }
                });
        addSubscribe(subscribe);
    }
}
