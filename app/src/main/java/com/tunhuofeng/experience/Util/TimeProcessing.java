package com.tunhuofeng.experience.Util;

import android.annotation.SuppressLint;
import android.content.Context;


import com.tunhuofeng.experience.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by whx on 2016/12/12.
 */

public class TimeProcessing {

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatterOne = new SimpleDateFormat("yyyy年MM月dd日");
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatterTwo = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatterThree = new SimpleDateFormat("yyyy.MM.dd");
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatterFour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @param strTime yyyy-MM-dd
     * @return yyyy.MM.dd
     */
    public static String getFormatThree(String strTime) {
        return strTime.replaceAll("-", ".");
    }

    /**
     * @param strTime yyyy.MM.dd
     * @return yyyy-MM-dd
     */
    public static String getFormatTwo(String strTime) {
        return strTime.replaceAll("\\.", "-");
    }

    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String date = sDateFormat.format(new Date());

    /**
     *
     */
    public static String getFormatFour(String strTime){
        String str =strTime.replaceAll("-", ".");
        String [] strs= str.split(":");

        return strs[0]+":"+strs[1];
    }

    /**
     * @return 2016年12月12日 周一
     */
    public static String getTimeAndWeek(Context context) {
        Date curDate = new Date(System.currentTimeMillis());
        String strTime = formatterOne.format(curDate);
        return strTime + "\r" + getWeek(strTime, context, 1);
    }

    /**
     * 获取今天日期
     *
     * @return 2016-12-20
     */
    public static String getTodayTime() {
        Date curDate = new Date(System.currentTimeMillis());
        return formatterTwo.format(curDate);
    }

    /**
     * @return 2017-6-16 9:53:18
     */
    public static String getNewTime(){
        String date = formatterFour.format(new Date());
        return date;
    }

    /**
     * 获取今天日期
     *
     * @return 2016.12.20
     */
    public static String getTodayTimeThree() {
        Date curDate = new Date(System.currentTimeMillis());
        return formatterThree.format(curDate);
    }



    /**
     * 以月获取几号
     */
    public static int getMonthDay(){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(getTodayTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 以年获取几号
     */
    public static int getYearDay(){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(getTodayTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * @param strTime 2016年12月12日 = 1 或 2016-12-20 = 2
     * @param state   1                 2
     */
    public static String getWeek(String strTime, Context context, int state) {
        String timeWeek = "";
        Calendar c = Calendar.getInstance();
        try {
            if (state == 1) {
                c.setTime(formatterOne.parse(strTime));
            } else {
                c.setTime(formatterTwo.parse(strTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                timeWeek = context.getString(R.string.week_1);
                break;
            case 2:
                timeWeek = context.getString(R.string.week_2);
                break;
            case 3:
                timeWeek = context.getString(R.string.week_3);
                break;
            case 4:
                timeWeek = context.getString(R.string.week_4);
                break;
            case 5:
                timeWeek = context.getString(R.string.week_5);
                break;
            case 6:
                timeWeek = context.getString(R.string.week_6);
                break;
            case 7:
                timeWeek = context.getString(R.string.week_7);
                break;
        }
        return timeWeek;
    }

    /**
     * @param strTime 2016-12-20
     *                1周日
     *                2周一
     *                3周二
     *                4周三
     *                5周四
     *                6周五
     *                7周六
     */
    public static int getWeekTwo(String strTime) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(strTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 昨天 -1
     */
    public static String getYesterday(String strTime) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(strTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int day = c.get(Calendar.DAY_OF_MONTH) - 1;
        c.set(Calendar.DAY_OF_MONTH, day);
        return formatterTwo.format(c.getTime());
    }

    /**
     * @param strTime 当天时间
     * @param days    减去天数
     * @return 2016-12-24
     */
    public static String getWeek(String strTime, int days) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(strTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int day = c.get(Calendar.DAY_OF_MONTH) - days;
        c.set(Calendar.DAY_OF_MONTH, day);
        return formatterTwo.format(c.getTime());
    }

    /**
     * 当周起始日
     *
     * @return 2016-12-21
     */
    public static String getWeek(String strTime) {
        switch (getWeekTwo(strTime)) {
            case 1://-6
                return getWeek(strTime, 6);
            case 2://-0
                return getWeek(strTime, 0);
            case 3://-1
                return getWeek(strTime, 1);
            case 4://-2
                return getWeek(strTime, 2);
            case 5://-3
                return getWeek(strTime, 3);
            case 6://-4
                return getWeek(strTime, 4);
            case 7://-5
                return getWeek(strTime, 5);
            default:
                return "";
        }
    }


    /**
     * 当前月份
     */
    public static String getMonth(String strTime) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(strTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.valueOf(c.get(Calendar.MONTH) + 1);
    }

    /**
     * 当月起始日
     *
     * @return 2016-12-1
     */
    public static String getMonth() {
        String today = getTodayTime();
        return getYear(today) + "-" + getMonth(today) + "-1";
    }

    /**
     * 当季起始日
     *
     * @return 2016-1-1 2016-4-1 2016-7-1 2016-10-1
     */
    public static String getQuarter() {
        String today = getTodayTime();
        String month = "";
        switch (getMonth(today)) {
            case "1":
            case "2":
            case "3":
                month = "-1";
                break;
            case "4":
            case "5":
            case "6":
                month = "-4";
                break;
            case "7":
            case "8":
            case "9":
                month = "-7";
                break;
            case "10":
            case "11":
            case "12":
                month = "-10";
                break;
        }
        return getYear(today) + month + "-1";
    }

    /**
     * 当年起始日
     *
     * @return 2016-1-1
     */
    public static String getYearStart() {
        return getYear(getTodayTime()) + "-1" + "-1";
    }


    /**
     * 当前年份
     */
    public static String getYear(String strTime) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatterTwo.parse(strTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.valueOf(c.get(Calendar.YEAR));

    }


    /**
     * 比较时间
     *
     * @param start 起始时间
     * @param end   结束时间
     * @return 起始时间 > 结束时间 false   起始时间 < 结束时间 true
     */
    public static boolean dateCompare(String start, String end) {
        //设定时间的模板
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //得到指定模范的时间
        Date s = null;
        Date e = null;
        try {
            s = sdf.parse(start);
            e = sdf.parse(end);
        } catch (ParseException err) {
            err.printStackTrace();
        }
        //比较
        if (s.getTime() > e.getTime()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return by Hankkin at:2015-10-07 21:16:43
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


}
