package com.lgy.drive.utils.imageutils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;
import com.lgy.drive.MainApplication;

import java.io.File;

/**
 * Created by ${lgy} on 2018/1/2914:20
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class MyGlideModule  implements GlideModule{
    private static final int M = 1024 * 1024;
    private static final int MAX_DISK_CACHE_SIZE = 256 * M;
    private static DiskCache diskCache = null;

    /**
     * ************************ Disk Cache ************************
     */
    private static synchronized DiskCache getDiskCache() {
        if (diskCache == null) {
            diskCache = createDiskCache();
        }
        return diskCache;
    }

    private static synchronized DiskCache createDiskCache() {
        final Context context = MainApplication.getInstance();
        File cacheDir=new File(context.getPackageName() + "/cache/image/");
        if (!cacheDir.isDirectory()) {
            cacheDir.mkdirs();
        }
        return DiskLruCacheWrapper.get(cacheDir, MAX_DISK_CACHE_SIZE);
    }

    /**
     * ************************ Memory Cache ************************
     */

    static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * ************************ GlideModule override ************************
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return getDiskCache();
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
