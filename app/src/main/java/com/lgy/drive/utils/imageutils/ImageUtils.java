package com.lgy.drive.utils.imageutils;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.model.CropOptions;

import java.io.File;

/**
 * Created by ${lgy} on 2018/3/2613:33
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class ImageUtils {

    /**
     * 创建剪切图片存放的位置
     *
     * @return
     */
    public static Uri getImageUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/cache/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    public static CropOptions getCropOptions(int height,int width) {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }
}
