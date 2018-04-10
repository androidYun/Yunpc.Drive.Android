package com.lgy.drive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lgy.drive.MainActivity;
import com.lgy.drive.PresneterView.LoginView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.enumbean.DriveStateEnum;
import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.model.http.resp.LoginResp;
import com.lgy.drive.persenter.LoginPresenter;
import com.lgy.drive.utils.sp.UserSpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/3/1212:41
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.ev_account)
    EditText evAccount;
    @BindView(R.id.ev_pwd)
    EditText evPwd;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void showContent(LoginResp loginResp) {
        String account = evAccount.getText().toString();
        String password = evPwd.getText().toString();
        UserSpUtils.setUserId(loginResp.data.drive.driveid);
        UserSpUtils.setPhone(account);
        UserSpUtils.getPassword(password);
        startActivity(new Intent(this, MainActivity.class));
        UserSpUtils.setToken(loginResp.data.token);
        UserSpUtils.setDrivebean(loginResp.data.drive);
        if (loginResp.data.drive.state == DriveStateEnum.WEI_SHENHE.getCode() || loginResp.data.drive.state == DriveStateEnum.WAN_USERINFOR.getCode()) {
            Intent intent = new Intent(this, CompleteUserInforActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DriveBean.class.getName(), loginResp.data.drive);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (loginResp.data.drive.state == DriveStateEnum.SHENHE_SUCCESS.getCode()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    @OnClick({R.id.btn_confirm, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                String account = evAccount.getText().toString();
                String password = evPwd.getText().toString();
                mPresenter.doLogin(account, password);
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
        }

    }

    @Override
    public void refreshFaild(String msg) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
