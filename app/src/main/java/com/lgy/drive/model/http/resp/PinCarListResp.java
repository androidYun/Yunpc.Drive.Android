package com.lgy.drive.model.http.resp;

import java.util.List;

/**
 * Created by ${lgy} on 2018/3/1211:49
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class PinCarListResp {


    /**
     * code : 200
     * data : [{"recordId":4,"driveId":4,"startCidtycode":"151515151","startPlace":"郑州","passPlace":"机场-龙湖-东区","endCitycode":"0317555","arrivePlace":"鹿邑","recordContent":"车找人 有人拼车打电话13014688217，鸽子勿扰","startTime":1520569242000,"endTime":1520569242000,"rideNumber":5,"createTime":1520569242000,"recordState":0,"drive":{"driveId":4,"driveName":"李前进","drivePhone":"13014688217","drivePassword":"123456","driveCarno":"123456","driveNumber":"123456","driveRemainnumber":6,"driveHead":"http://img3.imgtn.bdimg.com/it/u=19109290,1081796615&fm=27&gp=0.jpg","driveIdcardPicture":"http://himg2.huanqiu.com/attachment2010/2017/0122/20170122022728341.jpg","driveIdcardHeadPicture":"https://gss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/a1ec08fa513d26977238c1ac5efbb2fb4216d85c.jpg","driveLicense":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=448201199,3292871471&fm=27&gp=0.jpg","driveState":2,"driveIntegrate":100,"driveCreateTime":1520569111000}}]
     * message :
     */

    public int code;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * recordId : 4
         * driveId : 4
         * startCidtycode : 151515151
         * startPlace : 郑州
         * passPlace : 机场-龙湖-东区
         * endCitycode : 0317555
         * arrivePlace : 鹿邑
         * recordContent : 车找人 有人拼车打电话13014688217，鸽子勿扰
         * startTime : 1520569242000
         * endTime : 1520569242000
         * rideNumber : 5
         * createTime : 1520569242000
         * recordState : 0
         * drive : {"driveId":4,"driveName":"李前进","drivePhone":"13014688217","drivePassword":"123456","driveCarno":"123456","driveNumber":"123456","driveRemainnumber":6,"driveHead":"http://img3.imgtn.bdimg.com/it/u=19109290,1081796615&fm=27&gp=0.jpg","driveIdcardPicture":"http://himg2.huanqiu.com/attachment2010/2017/0122/20170122022728341.jpg","driveIdcardHeadPicture":"https://gss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/a1ec08fa513d26977238c1ac5efbb2fb4216d85c.jpg","driveLicense":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=448201199,3292871471&fm=27&gp=0.jpg","driveState":2,"driveIntegrate":100,"driveCreateTime":1520569111000}
         */

        public int recordId;
        public int driveId;
        public String startCidtycode;
        public String startPlace;
        public String passPlace;
        public String endCitycode;
        public String arrivePlace;
        public String recordContent;
        public long startTime;
        public long endTime;
        public int rideNumber;
        public long createTime;
        public int recordState;
        public DriveBean drive;

        public static class DriveBean {
            /**
             * driveId : 4
             * driveName : 李前进
             * drivePhone : 13014688217
             * drivePassword : 123456
             * driveCarno : 123456
             * driveNumber : 123456
             * driveRemainnumber : 6
             * driveHead : http://img3.imgtn.bdimg.com/it/u=19109290,1081796615&fm=27&gp=0.jpg
             * driveIdcardPicture : http://himg2.huanqiu.com/attachment2010/2017/0122/20170122022728341.jpg
             * driveIdcardHeadPicture : https://gss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/a1ec08fa513d26977238c1ac5efbb2fb4216d85c.jpg
             * driveLicense : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=448201199,3292871471&fm=27&gp=0.jpg
             * driveState : 2
             * driveIntegrate : 100
             * driveCreateTime : 1520569111000
             */

            public int driveId;
            public String driveName;
            public String drivePhone;
            public String drivePassword;
            public String driveCarno;
            public String driveNumber;
            public int driveRemainnumber;
            public String driveHead;
            public String driveIdcardPicture;
            public String driveIdcardHeadPicture;
            public String driveLicense;
            public int driveState;
            public int driveIntegrate;
            public long driveCreateTime;
        }
    }
}
