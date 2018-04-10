package com.lgy.drive;

import android.support.multidex.MultiDexApplication;

/**
 * Created by ${lgy} on 2018/3/1211:38
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class MainApplication extends MultiDexApplication {
    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
