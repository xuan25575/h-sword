package com.hxy.joda;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * @Author huang_2
 * @Date 2020/4/24 8:20 下午
 * @Description TODO
 */



public class DateTimeUtils {




    /**
     * @param date
     * @param anotherDate
     * @return boolean
     * @Title:判断两个日期是否在同一天
     * @methodName: isSameDay
     * @Description:
     * @author: 王延飞
     * @date: 2017-05-02 20:19
     */
    public static boolean isSameDay(String date, String anotherDate) {

        boolean res = false;
        // 初始化时间
        DateTime dateTime = new DateTime();

        DateTime dt1 = new DateTime(date);
        DateTime dt2 = new DateTime(anotherDate);

        int intervalDays = Days.daysBetween(dt1, dt2).getDays();

        if (intervalDays == 0) {
            res = true;
        }

        return res;

    }

    /**
     * @return String
     * @Title: 获取当前系统时间 <br/>
     * <br/>
     * <格式 :yyyy-MM-dd HH:mm:ss>
     * @Description:
     * @author: 王延飞
     * @date:2016年12月24日 上午10:59:14
     */
    public static String getCurSysTime() {
        // 初始化时间
        DateTime dateTime = new DateTime();
        return dateTime.toString(SysConstant.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param
     * @return java.lang.String
     * @Title: 获取当前系统时间的年月 <br/>
     * <br/>
     * <格式 :yyyy年MM月>
     * @Description:
     * @author: 王延飞
     * @date: 2017-01-23 11:22
     */
    public static String getCurSysYearMonth() {

        // 初始化时间
        DateTime dateTime = new DateTime();
        return dateTime.toString("yyyy年MM月");
    }


    public static String getCurSysYear() {

        // 初始化时间
        DateTime dateTime = new DateTime();
        return dateTime.toString("yyyy");
    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期获得星期
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:22:23
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return weekDaysName[intWeek];
        // return weekDaysCode[intWeek];
    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期<String>获得【星期】
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:50:35
     */
    public static String getWeekOfDateStr(String date) {
        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }
        // Get the day of week field value.
        int dayOfWeek = dateTime.getDayOfWeek();

        switch (dayOfWeek) {
            case 1:
                res = "周一";
                break;
            case 2:
                res = "周二";
                break;
            case 3:
                res = "周三";
                break;
            case 4:
                res = "周四";
                break;
            case 5:
                res = "周五";
                break;
            case 6:
                res = "周六";
                break;
            case 7:
                res = "周日";
                break;

            default:
                break;
        }
        return res;
    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期<String>获得【月份】
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:50:35
     */
    public static String getMonthOfDateStr(String date) {
        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }
        // Get the month of year field value
        int monthOfYear = dateTime.getMonthOfYear();

        res = String.valueOf(monthOfYear);

        return res;

    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期<String>获得【年份】
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:50:35
     */
    public static String getYearOfDateStr(String date) {
        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }
        // Get the year field value.
        int year = dateTime.getYear();

        res = String.valueOf(year);

        return res;

    }

    /**
     * @param date
     * @return java.lang.String
     * @Title: 根据日期<年份月份>获得【年份】
     * @methodName: getYearOfYearMonth
     * @Description:
     * @author: 王延飞
     * @date: 2017-01-23 14:47
     */
    public static String getYearOfYearMonth(String date) {

        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern("yyyy年MM月").parseDateTime(date);
        }
        // Get the year field value.
        int year = dateTime.getYear();

        res = String.valueOf(year);

        return res;

    }

    /**
     * @param date
     * @return java.lang.String
     * @Title: 根据日期<年份月份>获得【月份】
     * @methodName: getMonthOfYearMonth
     * @Description:
     * @author: 王延飞
     * @date: 2017-01-23 14:50
     */
    public static String getMonthOfYearMonth(String date) {

        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern("yyyy年MM月").parseDateTime(date);
        }
        // Get the year field value.
        int monthOfYear = dateTime.getMonthOfYear();

        res = String.valueOf(monthOfYear) + "月";

        return res;

    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期<String>获得【月-日】
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:50:35
     */
    public static String getMonthAndDayOfDateStr(String date) {
        // 返回值
        String res = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }
        // Get the month of year field value
        int monthOfYear = dateTime.getMonthOfYear();


        int dayOfMonth = dateTime.getDayOfMonth();

        res = monthOfYear + "-" + dayOfMonth;

        return res;

    }

    /**
     * @param date
     * @return String
     * @Title: 根据日期<String>获得【年-月】
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:50:35
     */
    public static String getYearAndMonthOfDateStr(String date) {
        // 返回值
        String res = "";
        String month = "";
        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }

        // Get the year field value.
        int year = dateTime.getYear();

        // Get the month of year field value
        int monthOfYear = dateTime.getMonthOfYear();

        if (monthOfYear < 10) {
            month = "0" + monthOfYear;
        } else {
            month = String.valueOf(monthOfYear);
        }


        res = year + "年" + month + "月";
        return res;

    }

    /**
     * @param date
     * @return DateTime
     * @Title: 将时间字符串转换成DateTime对象<yyyy-MM-dd                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               HH:mm:ss>
     * @Description:
     * @author: 王延飞
     * @date:2016年12月30日 下午8:21:30
     */
    public static DateTime str2DateTime(String date) {

        DateTime dateTime = null;
        if (StringUtils.isNotBlank(date)) {
            dateTime = DateTimeFormat.forPattern(SysConstant.YYYY_MM_DD_HH_MM_SS).parseDateTime(date);
        }
        return dateTime;
    }

    /**
     * @param
     * @return long
     * @Title: 当前时间的毫秒数
     * @methodName: timestamp
     * @Description:
     * @author: 王延飞
     * @date: 2018-06-21 17:28
     */
    public static long timestamp() {

        DateTime dateTime = new DateTime();
        long millis = dateTime.getMillis();
        return millis;
    }


    /**
     * @param date
     * @return int
     * @Title: 和当前系统时间相隔的分钟数
     * @methodName: minutesBetween
     * @Description:
     */
    public static int minutesBetween(Date date) {

        // 系统当前时间
        DateTime SysTime = new DateTime();

        DateTime dateTime = null;
        if (date != null) {
            dateTime = new DateTime(date);
        }

        int minutes = Minutes.minutesBetween(dateTime, SysTime).getMinutes();

        return minutes;
    }


    public static void main(String[] args) throws ParseException {
        // System.out.println("系统当前时间： "+DateTimeUtils.getCurSysTime());
//		DateTime str2DateTime = DateTimeUtils.str2DateTime("2016-03-01 22:50:26");
//
//		System.out.println("系统当前时间： " + str2DateTime);

//		String weekOfDateStr = DateTimeUtils.getWeekOfDateStr("2016-11-27 10:50:26");
//
//		System.out.println("根据日期<String>获得【星期】: " + weekOfDateStr);

        // 根据日期<String>获得月份
//		String monthOfYear = DateTimeUtils.getMonthOfDateStr("2016-02-05 22:50:26");
//
//		System.out.println("根据日期<String>获得【月份】: " + monthOfYear);
//
//
//		String yearOfDateStr = DateTimeUtils.getYearOfDateStr("2016-02-05 22:50:26");
//
//		System.out.println("根据日期获得【年份】: " + yearOfDateStr);
//
//		String year_MonthOfDateStr = DateTimeUtils.getMonthAndDayOfDateStr("2016-12-25 22:50:26");
//		System.out.println("根据日期<String>获得【月-日】: " + year_MonthOfDateStr);


	/*	System.out.println("系统当前时间： "+DateTimeUtils.getCurSysYearMonth());
        System.out.println("系统当前年份： "+DateTimeUtils.getCurSysYear());
		System.out.println("根据日期<String>获得【年-月】： "+DateTimeUtils.getYearAndMonthOfDateStr("2017-12-05 22:50:26"));
		System.out.println("根据日期<String>获得【年】： "+DateTimeUtils.getYearOfDateStr("2017-12-05 22:50:26"));
		System.out.println("根据日期<年份月份>获得【年份】： "+DateTimeUtils.getYearOfYearMonth("2017年12月"));
		System.out.println("根据日期<年份月份>获得【月份】： "+DateTimeUtils.getMonthOfYearMonth("2017年01月"));*/

//        System.out.println("根据日期<年份月份>获得【星期】： " + DateTimeUtils.getWeekOfDateStr("2017-03-6 22:50:26"));
//
//        boolean sameDay = isSameDay("20170501", "20170502");
//        System.out.println("isSameDay： " + sameDay);


        String dateString = "2017-06-29 14:11:28";

        SimpleDateFormat sdf = new SimpleDateFormat(SysConstant.YYYY_MM_DD_HH_MM_SS);
        Date date2 = sdf.parse(dateString);

        System.out.println("String 转 Date" + date2);

        int minutesBetween = minutesBetween(date2);
        System.out.println(minutesBetween + " 分钟 ");
        System.out.println(minutesBetween - 15 + " 分钟 ");
        System.out.println("是否小于15分钟： ");
        System.out.println(minutesBetween <= 15);
        System.out.println("时间戳：" + timestamp());




    }

}
