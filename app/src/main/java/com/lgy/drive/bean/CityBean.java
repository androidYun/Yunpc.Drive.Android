package com.lgy.drive.bean;

/**
 * Created by ${lgy} on 2018/4/308:58
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class CityBean {
    private String cityCode;

    private String cityName;

    public CityBean(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
