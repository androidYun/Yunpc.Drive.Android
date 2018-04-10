package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.LoginView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.LoginResp;
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

public class LoginPresenter extends RxPresenter<LoginView>   {

    @Inject
    public LoginPresenter() {
    }

    public void doLogin(String phone, String password) {
        Subscription subscribe = RetrofitHelper.getUserApi().doLogin(phone, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<LoginResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(LoginResp loginResp) {
                        mView.showContent(loginResp);
                    }
                });
        addSubscribe(subscribe);
    }
}
