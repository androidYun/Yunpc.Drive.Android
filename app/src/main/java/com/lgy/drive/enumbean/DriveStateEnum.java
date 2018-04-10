package com.lgy.drive.enumbean;

/**
 * Created by ${lgy} on 2018/3/3014:27
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public enum DriveStateEnum {
    WEI_SHENHE(0, "未审核"), WAN_USERINFOR(1, "完善个人信息"), WAN_CARINFOR(2, "完善车辆信息"), SHENHE_SUCCESS(3, "审核成功"), WSHENHE_FAIL(4, "审核失败"),;
    private int code;

    private String state;

    DriveStateEnum(int code, String state) {
        this.code = code;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
