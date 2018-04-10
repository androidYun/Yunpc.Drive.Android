package com.lgy.drive.model.http.resp;

/**
 * Created by ${lgy} on 2018/3/2610:36
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class UploadResp {

    /**
     * code : 200
     * data : {"imageUrl":"E:\\java\\Yunpc\\src\\main\\webapp\\images\\2018\\3\\dcef0dc0-aad4-4811-9118-8e6aead37123.jpg"}
     * message :
     */

    public int code;
    public DataBean data;
    public String message;

    public static class DataBean {
        /**
         * imageUrl : E:\java\Yunpc\src\main\webapp\images\2018\3\dcef0dc0-aad4-4811-9118-8e6aead37123.jpg
         */

        public String imageUrl;
    }
}
