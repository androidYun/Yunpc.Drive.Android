package com.lgy.drive.PresneterView;


import com.lgy.drive.base.BaseView;
import com.lgy.drive.model.http.resp.AllOrderResp;

/**
 * Created by ${lgy} on 2018/3/1317:46
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface AllOrderView extends BaseView {

    void onAllOrderSuccess(AllOrderResp allOrderResp);
}
