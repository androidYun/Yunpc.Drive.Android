package com.lgy.drive.ui.activity;

import android.widget.Button;
import android.widget.EditText;

import com.lgy.drive.PresneterView.ForgetPwdView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.model.http.resp.ForgetPwdResp;
import com.lgy.drive.persenter.ForgetPwdPresenter;
import com.lgy.drive.utils.sp.UserSpUtils;
import com.lgy.drive.view.NaviTitleView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/3/1614:25
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class ForgetPasswordActivity extends BaseMvpActivity<ForgetPwdPresenter> implements ForgetPwdView {
    @BindView(R.id.navi_view)
    NaviTitleView naviView;
    @BindView(R.id.ev_old_pwd)
    EditText evOldPwd;
    @BindView(R.id.ev_new_pwd)
    EditText evNewPwd;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;


    @Override
    protected int getLayout() {
        return R.layout.activity_forget_pwd;
    }


    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        String oldPwd = evOldPwd.getText().toString().trim();
        String newPwd = evNewPwd.getText().toString().trim();
        mPresenter.doForgetPwd(UserSpUtils.getUserId(), oldPwd, newPwd);
    }

    @Override
    public void onForgetPwdSuccess(ForgetPwdResp forgetPwdResp) {
        UserSpUtils.getPassword(evNewPwd.getText().toString().trim());
        finish();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
