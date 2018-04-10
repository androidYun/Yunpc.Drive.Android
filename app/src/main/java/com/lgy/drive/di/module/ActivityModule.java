package com.lgy.drive.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ${lgy} on 2018/3/1614:35
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */
@Module
public class ActivityModule {

    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }
}
