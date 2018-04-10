package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.ForgetPwdView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.ForgetPwdResp;
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

public class ForgetPwdPresenter extends RxPresenter<ForgetPwdView> {
    @Inject
    public ForgetPwdPresenter() {
    }

    public void doForgetPwd(int userId, String oldPwd, String newPwd) {
        Subscription subscribe = RetrofitHelper.getUserApi().doForgetPwd(userId, oldPwd, newPwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<ForgetPwdResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(ForgetPwdResp forgetPwdResp) {
                        mView.onForgetPwdSuccess(forgetPwdResp);
                    }
                });
        addSubscribe(subscribe);
    }
}
