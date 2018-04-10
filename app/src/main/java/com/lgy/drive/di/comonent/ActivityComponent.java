package com.lgy.drive.di.comonent;

import android.app.Activity;

import com.lgy.drive.MainActivity;
import com.lgy.drive.di.module.ActivityModule;
import com.lgy.drive.ui.activity.AddRecordActivity;
import com.lgy.drive.ui.activity.CompleteCarInforActivity;
import com.lgy.drive.ui.activity.CompleteUserInforActivity;
import com.lgy.drive.ui.activity.ForgetPasswordActivity;
import com.lgy.drive.ui.activity.LoginActivity;
import com.lgy.drive.ui.activity.OrderActivity;
import com.lgy.drive.ui.activity.OrderListActivity;
import com.lgy.drive.ui.activity.PinCarDetailActivity;
import com.lgy.drive.ui.activity.RegisterActivity;
import com.lgy.drive.ui.activity.WecomeActivity;

import dagger.Component;

/**
 * Created by ${lgy} on 2018/3/1614:35
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(LoginActivity loginActivity);

    void inject(OrderListActivity orderListActivity);

    void inject(PinCarDetailActivity pinCarDetailActivity);

    void inject(RegisterActivity registerActivity);

    void inject(WecomeActivity wecomeActivity);

    void inject(CompleteUserInforActivity completeUserInforActivity);


    void inject(CompleteCarInforActivity completeCarInforActivity);

    void inject(AddRecordActivity addRecordActivity);

    void inject(OrderActivity orderActivity);

}
