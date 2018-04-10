package com.lgy.drive.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${lgy} on 2018/3/1508:34
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class TimeUtils {
    public final static String pattern_ymd="yyyy-MM-dd";
    public final static String pattern_ymd_hms="yyyy-MM-dd  HH:MM:SS";

    public final static String pattern_ymd_h = "yyyy-MM-dd Hh";
    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        return format.format(date);
    }
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String getTime(String time, String pattern) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(date);
    }
}
