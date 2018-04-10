package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.RegisterView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.RegsiterResp;
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

public class RegsiterPresenter extends RxPresenter<RegisterView> {

    @Inject
    public RegsiterPresenter() {
    }

    public void doRegister(String phone, String password) {
        Subscription subscribe = RetrofitHelper.getUserApi().doRegister(phone, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<RegsiterResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(RegsiterResp regsiterResp) {
                        mView.onResgiterSuccess(regsiterResp);
                    }
                });
        addSubscribe(subscribe);
    }
}
