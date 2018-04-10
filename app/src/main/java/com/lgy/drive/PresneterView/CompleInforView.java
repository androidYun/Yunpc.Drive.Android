package com.lgy.drive.PresneterView;

import com.lgy.drive.base.BaseView;
import com.lgy.drive.model.http.resp.DriveResp;

/**
 * Created by ${lgy} on 2018/3/2917:01
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface CompleInforView extends BaseView {

    void onCompleteSuccess(DriveResp driveResp);

    void onCompleteCarSuccess(DriveResp driveResp);

    void upLoadHeadImage(String path);


    void upLoadIDCardImage(String path);

    void upDriveLicenseImage(String path);
}
