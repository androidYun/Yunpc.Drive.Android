package com.lgy.drive.PresneterView;

import com.lgy.drive.base.BaseView;
import com.lgy.drive.model.http.resp.AppointmentCarResp;
import com.lgy.drive.model.http.resp.HandlerOrderResp;
import com.lgy.drive.model.http.resp.OrderResp;
import com.lgy.drive.model.http.resp.UploadResp;

/**
 * Created by ${lgy} on 2018/3/1317:46
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface MainView extends BaseView {

    void onAppointmentSuccess(AppointmentCarResp appointmentCarResp);

    void getOrderSuccess(OrderResp orderResp);

    void uploadImages(UploadResp uploadResp);

    void handlerSuccess(HandlerOrderResp handlerOrderResp);
}
