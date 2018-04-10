package com.lgy.drive.model.http.resp;

/**
 * Created by ${lgy} on 2018/4/416:39
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class OrderBean {
    /**
     * orderid : 6
     * startcitycode : 122
     * latitude : 114
     * longitude : 46
     * endcitycode : 123
     * driveid : 7
     * orderstate : 0
     * recordid : 18
     * userid : 5
     * takenumber : 1
     * record : {"recordid":18,"driveid":7,"phone":"13098834723","startcitycode":"122","startplace":"鹿邑","passplace":"刚去","endcitycode":"123","arriveplace":"郑州","content":"诚信拼车","starttime":"2018-04-08 14:04:00.0","endtime":null,"takenumber":1,"createtime":"2018-04-04 02:56:40.0","recordstate":0}
     */

    public int orderid;
    public String startcitycode;
    public int latitude;
    public int longitude;
    public String endcitycode;
    public int driveid;
    public int orderstate;
    public int recordid;
    public int userid;
    public int takenumber;
    public RecordBean record;

    public static class RecordBean {
        /**
         * recordid : 18
         * driveid : 7
         * phone : 13098834723
         * startcitycode : 122
         * startplace : 鹿邑
         * passplace : 刚去
         * endcitycode : 123
         * arriveplace : 郑州
         * content : 诚信拼车
         * starttime : 2018-04-08 14:04:00.0
         * endtime : null
         * takenumber : 1
         * createtime : 2018-04-04 02:56:40.0
         * recordstate : 0
         */

        public int recordid;
        public int driveid;
        public String phone;
        public String startcitycode;
        public String startplace;
        public String passplace;
        public String endcitycode;
        public String arriveplace;
        public String content;
        public String starttime;
        public Object endtime;
        public int takenumber;
        public String createtime;
        public int recordstate;
    }
}
