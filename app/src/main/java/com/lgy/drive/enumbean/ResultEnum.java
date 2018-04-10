package com.lgy.drive.enumbean;

/**
 * Created by ${lgy} on 2018/3/1314:10
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public enum ResultEnum {
    RESPONSE_SUCCESS(200, "请求成功"),
    RESPONSE_FAIL(100, "请求失败");
    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
