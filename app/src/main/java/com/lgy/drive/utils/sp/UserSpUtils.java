package com.lgy.drive.utils.sp;

import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.utils.StringUtils;

/**
 * Created by ${lgy} on 2018/3/1315:32
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class UserSpUtils {
    private final static String USER_ID = "userId";

    private final static String PHONE = "phone";

    private final static String PASSWORD = "password";

    private final static String TOKEN = "TOKEN";

    private final static String DRIVEBEAN = "drivebean";

    private static String mToken;


    public static int getUserId() {
        return (int) SPUtil.get(USER_ID, 0);
    }

    public static void setUserId(int userId) {
        SPUtil.put(USER_ID, userId);
    }

    public static String getPhone() {
        return (String) SPUtil.get(PHONE, "");
    }

    public static void setPhone(String phone) {
        SPUtil.put(PHONE, phone);
    }

    public static String getPassword() {
        return (String) SPUtil.get(PASSWORD, "");
    }

    public static void getPassword(String password) {
        SPUtil.put(PASSWORD, password);
    }

    public static String getToken() {
        if (StringUtils.isEmpty(mToken)) {
            mToken = SPUtil.getString(TOKEN, "");
        }
        return mToken;
    }

    public static void setToken(String token) {
        mToken = token;
        SPUtil.put(TOKEN, token);
    }

    public static void setDrivebean(DriveBean drivebean) {
        SPUtil.putBean(DRIVEBEAN, drivebean);
    }

    public static DriveBean getDriveBean() {
        return SPUtil.getBean(DRIVEBEAN, DriveBean.class);
    }
}
