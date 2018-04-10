package com.lgy.drive.model.http.resp;

/**
 * Created by ${lgy} on 2018/3/1314:08
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class BaseResp {

    public int code;

    public String message;

    @Override
    public String toString() {
        return "BaseResp{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
