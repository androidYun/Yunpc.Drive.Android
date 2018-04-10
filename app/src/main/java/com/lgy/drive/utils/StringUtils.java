package com.lgy.drive.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/1/23.
 */

public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String getReplaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    /**
     * 获取忽略条目
     *
     * @param pageSize  每次获取的数额
     * @param pageNumber 页码
     * @return
     */
    public static int getSkipCount(int pageSize, int pageNumber) {
        // TODO Auto-generated method stub
        int skipCount = pageSize * (pageNumber - 1);
        return skipCount;
    }
}
