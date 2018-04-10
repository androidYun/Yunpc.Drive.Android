package com.lgy.drive.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.lgy.drive.MainActivity;
import com.lgy.drive.PresneterView.LoginView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.enumbean.DriveStateEnum;
import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.model.http.resp.LoginResp;
import com.lgy.drive.persenter.LoginPresenter;
import com.lgy.drive.utils.StringUtils;
import com.lgy.drive.utils.sp.UserSpUtils;


/**
 * Created by ${lgy} on 2018/3/1315:59
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class WecomeActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {


    @Override
    protected int getLayout() {
        return R.layout.activity_wecome;
    }

    @Override
    protected void init() {
        super.init();
        String phone = UserSpUtils.getPhone();
        String password = UserSpUtils.getPassword();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            mPresenter.doLogin(phone, password);
        }
    }

    @Override
    public void showContent(LoginResp loginResp) {
        UserSpUtils.setToken(loginResp.data.token);
        if (loginResp.data.drive.state == DriveStateEnum.WEI_SHENHE.getCode() || loginResp.data.drive.state == DriveStateEnum.WAN_USERINFOR.getCode()) {
            Intent intent = new Intent(this, CompleteUserInforActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DriveBean.class.getName(), loginResp.data.drive);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(loginResp.data.drive.state==DriveStateEnum.SHENHE_SUCCESS.getCode()){
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void refreshFaild(String msg) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
