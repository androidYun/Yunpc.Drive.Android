package com.lgy.drive.PresneterView;

import com.lgy.drive.base.BaseView;
import com.lgy.drive.model.http.resp.LoginResp;

/**
 * Created by ${lgy} on 2018/3/1315:09
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface LoginView extends BaseView{
    void showContent(LoginResp loginResp);

    void refreshFaild(String msg);
}
