package com.lgy.drive.base;

import android.content.Intent;
import android.os.Bundle;

import com.lgy.drive.di.comonent.ActivityComponent;
import com.lgy.drive.di.comonent.DaggerActivityComponent;
import com.lgy.drive.di.module.ActivityModule;
import com.lgy.drive.ui.activity.LoginActivity;
import com.lgy.drive.utils.LogUtil;
import com.lgy.drive.utils.ToastUtil;

import javax.inject.Inject;

/**
 * Description: MVP Activity基类
 * Creator: yxc
 * date: 17/9/14
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(this, msg);
        LogUtil.d(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initInject();

    @Override
    public void tokenLose() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
