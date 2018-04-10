package com.lgy.drive.model.http.resp;

import java.util.List;

/**
 * Created by ${lgy} on 2018/4/314:57
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class OrderResp {


    /**
     * code : 200
     * data : {"dataList":[{"orderid":9,"startcitycode":"122","latitude":114,"longitude":46,"endcitycode":"123","driveid":7,"orderstate":0,"recordid":24,"userid":5,"takenumber":1,"record":{"recordid":24,"driveid":7,"phone":"13098834723","startcitycode":"122","startplace":"鹿邑","passplace":"新郑机场","endcitycode":"123","arriveplace":"郑州","content":"诚信拼车","starttime":"2018-04-10 16:04:00.0","endtime":null,"takenumber":1,"createtime":"2018-04-08 04:49:15.0","recordstate":0}}],"pageInfo":{"pageSize":10,"pageNumber":1,"totalPageCount":1,"totalCount":1}}
     * message : 请求成功
     */

    public int code;
    public DataBean data;
    public String message;

    public static class DataBean {
        /**
         * dataList : [{"orderid":9,"startcitycode":"122","latitude":114,"longitude":46,"endcitycode":"123","driveid":7,"orderstate":0,"recordid":24,"userid":5,"takenumber":1,"record":{"recordid":24,"driveid":7,"phone":"13098834723","startcitycode":"122","startplace":"鹿邑","passplace":"新郑机场","endcitycode":"123","arriveplace":"郑州","content":"诚信拼车","starttime":"2018-04-10 16:04:00.0","endtime":null,"takenumber":1,"createtime":"2018-04-08 04:49:15.0","recordstate":0}}]
         * pageInfo : {"pageSize":10,"pageNumber":1,"totalPageCount":1,"totalCount":1}
         */

        public PageInfoBean pageInfo;
        public List<OrderBean> dataList;

        public static class PageInfoBean {
            /**
             * pageSize : 10
             * pageNumber : 1
             * totalPageCount : 1
             * totalCount : 1
             */

            public int pageSize;
            public int pageNumber;
            public int totalPageCount;
            public int totalCount;
        }
    }
}
