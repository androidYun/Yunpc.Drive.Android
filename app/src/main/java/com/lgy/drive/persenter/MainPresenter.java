package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.MainView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.AppointmentCarResp;
import com.lgy.drive.model.http.resp.HandlerOrderResp;
import com.lgy.drive.model.http.resp.OrderResp;
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

public class MainPresenter extends RxPresenter<MainView> {

    @Inject
    public MainPresenter() {
    }

    public void doAppointmentCar(int userid, int recordid) {
        Subscription subscribe = RetrofitHelper.getUserApi().appointmentCar(userid, recordid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<AppointmentCarResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(AppointmentCarResp AppointmentCarResp) {
                        mView.onAppointmentSuccess(AppointmentCarResp);
                    }
                });
        addSubscribe(subscribe);
    }


    public void getOrderList(int state,int pageNumber,int pageSize) {
        RetrofitHelper.getUserApi().getOrderList(state, pageNumber, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<OrderResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(OrderResp orderResp) {
                        mView.getOrderSuccess(orderResp);
                    }
                });
    }

    public void handlerOrder(int state,int orderId) {
        RetrofitHelper.getUserApi().handlerOrder(state,orderId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<HandlerOrderResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(HandlerOrderResp handlerOrderResp) {
                        mView.handlerSuccess(handlerOrderResp);
                    }
                });
    }
}
