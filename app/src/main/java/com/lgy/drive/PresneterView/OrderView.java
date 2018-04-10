package com.lgy.drive.PresneterView;

import com.lgy.drive.base.BaseView;
import com.lgy.drive.model.http.resp.OrderResp;

/**
 * Created by ${lgy} on 2018/4/808:14
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface OrderView extends BaseView {

    void getOrderSuccess(OrderResp orderResp);

    void handlerSucdess();
}
