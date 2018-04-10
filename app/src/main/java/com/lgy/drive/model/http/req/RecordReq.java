package com.lgy.drive.model.http.req;

/**
 * Created by ${lgy} on 2018/4/308:41
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class RecordReq {

    private String phone;

    private String startcitycode;

    private String startplace;

    private String passplace;

    private String endcitycode;

    private String arriveplace;

    private String content;

    private String starttime;

    private Integer takenumber;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartcitycode() {
        return startcitycode;
    }

    public void setStartcitycode(String startcitycode) {
        this.startcitycode = startcitycode;
    }

    public String getStartplace() {
        return startplace;
    }

    public void setStartplace(String startplace) {
        this.startplace = startplace;
    }

    public String getPassplace() {
        return passplace;
    }

    public void setPassplace(String passplace) {
        this.passplace = passplace;
    }

    public String getEndcitycode() {
        return endcitycode;
    }

    public void setEndcitycode(String endcitycode) {
        this.endcitycode = endcitycode;
    }

    public String getArriveplace() {
        return arriveplace;
    }

    public void setArriveplace(String arriveplace) {
        this.arriveplace = arriveplace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Integer getTakenumber() {
        return takenumber;
    }

    public void setTakenumber(Integer takenumber) {
        this.takenumber = takenumber;
    }
}
