/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lgy.drive.utils.permission;

import android.content.Context;
import android.text.TextUtils;

import com.lgy.drive.R;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by YanZhenjie on 2018/1/1.
 */
public final class DefaultRationale implements Rationale {

    @Override
    public void showRationale(Context mContext, List<String> permissions, final RequestExecutor executor) {
        List<String> permissionNames = Permission.transformText(mContext, permissions);
        String message = mContext.getString(R.string.message_permission_rationale, TextUtils.join("\n", permissionNames));
        new SweetAlertDialog(mContext).setContentText(message).setTitleText("提示")
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        executor.execute();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        executor.cancel();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }
}