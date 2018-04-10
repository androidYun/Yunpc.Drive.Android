package com.lgy.drive.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.lgy.drive.PresneterView.RegisterView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.model.http.resp.RegsiterResp;
import com.lgy.drive.persenter.RegsiterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/3/1317:41
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class RegisterActivity extends BaseMvpActivity<RegsiterPresenter> implements RegisterView {
    @BindView(R.id.ev_account)
    EditText evAccount;
    @BindView(R.id.ev_pwd)
    EditText evPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;



    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }


    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String phone = evAccount.getText().toString();
        String password = evPwd.getText().toString();
        mPresenter.doRegister(phone, password);
    }

    @Override
    public void onResgiterSuccess(RegsiterResp regsiterResp) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
