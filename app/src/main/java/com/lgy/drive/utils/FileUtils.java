package com.lgy.drive.utils;

/**
 * Created by ${lgy} on 2018/3/2814:39
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class FileUtils {
    public static String getSuffix(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        } else {
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        }
    }
}
