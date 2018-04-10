package com.lgy.drive.model.http.resp;

/**
 * Created by ${lgy} on 2018/3/1211:49
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class LoginResp  extends BaseResp{


    /**
     * data : {"drive":{"driveid":5,"phone":"13098834723","name":null,"password":"a136735","sex":null,"age":null,"idcardno":null,"driveno":null,"idcardheadurl":null,"carnumber":null,"carname":null,"getlicensetime":null,"drivehead":null,"drivelicenseurl":null,"state":null,"creditscore":null,"createtime":null},"token":"5-40ad462934dd40f9a4a6b592b9c6ff52"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * drive : {"driveid":5,"phone":"13098834723","name":null,"password":"a136735","sex":null,"age":null,"idcardno":null,"driveno":null,"idcardheadurl":null,"carnumber":null,"carname":null,"getlicensetime":null,"drivehead":null,"drivelicenseurl":null,"state":null,"creditscore":null,"createtime":null}
         * token : 5-40ad462934dd40f9a4a6b592b9c6ff52
         */

        public DriveBean drive;

        public String token;


    }
}
