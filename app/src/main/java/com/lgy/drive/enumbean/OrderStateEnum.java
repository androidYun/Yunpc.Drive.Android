package com.lgy.drive.enumbean;

import java.io.Serializable;

/**
 * Created by ${lgy} on 2018/4/314:58
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容： 0 新订单  1 司机接受订单  2 司机拒绝订单  3 用户取消订单  4 订单完成
 */

public enum OrderStateEnum implements Serializable {
    NEW_ORDER(0, "新订单"), ACCEPT_ORDER(1, "司机确认接单"), CANCLE_ORDER(2, "取消订单"), REFUSE_ORDER(3, "拒绝订单"), FAIL_ORSER(4, "订单失败"), SUCCESS_ORDER(5, "订单成功"), ALL_ORDER(6, "全部订单");

    private int code;

    private String state;

    OrderStateEnum(int code, String state) {
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
