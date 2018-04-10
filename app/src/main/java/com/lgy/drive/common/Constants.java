package com.lgy.drive.common;

import com.lgy.drive.MainApplication;

import java.io.File;

/**
 * Created by ${lgy} on 2018/3/1211:17
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class Constants {
    public final static String BASE_URL = "http://192.168.80.65:8087/";

    public static final String PATH_DATA = MainApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";

    public static final int PAGE_SIZE = 10;//刷新页数
}
