package com.yxkong.demo.infrastructure.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理工具类
 *
 * @author jiang.li
 * @date 2013-12-18 11:22
 */
public class DateUtils {

    /**
     * 定义常量
     **/
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_LONG_STR = "yyyy-MM-dd kk:mm:ss.SSS";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_MONTH = "yyyy-MM";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_All_KEY_STR = "yyyyMMddHHmmss";

    /**
     * 给指定的日期加上(减去)月份
     *
     * @param date
     * @param pattern
     * @param num
     * @return
     */
    public static String addMoth(Date date, String pattern, int num) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.MONTH, num);
        return simpleDateFormat.format(calender.getTime());
    }

    /**
     * 给指定的时间加上(减去)天
     *
     * @param date
     * @param pattern
     * @param num
     * @return
     */
    public static String addDay(Date date, String pattern, int num) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DATE, num);
        return simpleDateFormat.format(calender.getTime());
    }

    /**
     * 给指定的时间加上(减去)天 返回Date
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDay(Date date, int num) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DATE, num);
        return calender.getTime();
    }

    /**
     * 给指定的时间加上(减去)小时
     *
     * @param date
     * @param pattern
     * @param num
     * @return
     * @author ducongcong
     * @createDate 2016年7月21日
     * @updateDate
     */
    public static String addHour(Date date, String pattern, int num) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.HOUR, num);
        return simpleDateFormat.format(calender.getTime());
    }

    /**
     * 给指定的时间加上(减去)分钟
     *
     * @param date
     * @param num
     * @return
     * @author ducongcong
     * @createDate 2016年7月21日
     * @updateDate
     */
    public static Date addMinute(Date date, int num) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.MINUTE, num);
        return calender.getTime();
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 根据对应的pattern 获取时间
     *
     * @param pattern
     * @return
     * @author ducongcong
     * @createDate 2016年7月21日
     * @updateDate
     */
    public static Date getDate(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateStr = df.format(new Date());
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 计算日期的加减
     *
     * @param pattern
     * @param num
     * @return
     * @author ducongcong
     * @createDate 2016年7月14日
     * @updateDate
     */
    public static Date getDate(String pattern, int num) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateStr = df.format(new Date());

        try {
            Date date = df.parse(dateStr);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.DATE, num);
            return calender.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 获取系统当前时间(指定返回类型)
     *
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param date 日期字符串
     * @return
     */
    public static Date parse(String date) {
        return parse(date, DATE_FULL_STR);
    }

    /**
     * 指定指定日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param
     * @return
     */
    public static int compareDateWithNow(Date date) {
        Date now = new Date();
        int rnum = date.compareTo(now);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @param
     * @return
     */
    public static int compareDateWithNow(long date) {
        long now = dateToUnixTimestamp();
        if (date > now) {
            return 1;
        } else if (date < now) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String TimeStamp2Date(long timestamp, String dateFormat) {
        String date = new SimpleDateFormat(dateFormat).format(new Date(timestamp));
        return date;
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String TimeStamp2Date(long timestamp) {
        String date = new SimpleDateFormat(DATE_FULL_STR).format(new Date(timestamp));
        return date;
    }

    /**
     * 时间转字符串
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * 获取系统当前日期和时间，格式为yyyy-MM-dd HH:mm:ss
     *
     * @return 返回计算后的日期时间（String）
     */
    public static String getCurrentDateTime() {
        return date2Str(new Date(), DATE_FULL_STR);
    }

    public static String getCurrentDateTimeyyyyMMddHHmmss() {
        return date2Str(new Date(), DATE_All_KEY_STR);
    }

    /**
     * sql.date 转字符串
     *
     * @param time
     * @return
     */
    public static String timestamp2Str(Timestamp time) {
        if (null != time) {
            Date date = new Date(time.getTime());
            return date2Str(date, DATE_FULL_STR);
        }
        return null;
    }

    /**
     * sql.date 转字符串
     *
     * @param time
     * @return
     */
    public static String timestampToPatten(Timestamp time, String patten) {
        if (null != time) {
            Date date = new Date(time.getTime());
            return date2Str(date, patten);
        }
        return null;
    }

    /**
     * 字符串转sql.date时间
     *
     * @param str yyyy-MM-dd 格式时间
     * @return
     */
    public static Timestamp strTimestampDay(String str) {
        if (null != str && !"".equals(str)) {
            Date date = parse(str, DATE_SMALL_STR);
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /**
     * 字符串转sql.date时间
     *
     * @param str yyyy-MM-dd HH:mm:ss 格式时间
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        if (null != str && !"".equals(str)) {
            Date date = parse(str, DATE_FULL_STR);
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /**
     * 将Timestamp类型对象转换为Date类型对象
     *
     * @param timestamp Timestamp类型对象
     * @return Date类型对象
     */
    public static Date timestampToDate(Timestamp timestamp) {
        Date date = new Date();
        date = timestamp;
        return date;
    }

    /**
     * 将Date类型对象转换为Timestamp类型对象
     *
     * @param date Date类型对象
     * @return Timestamp类型对象
     */
    public static Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 获取当前时间的Timestamp
     *
     * @return Timestamp类型对象
     */
    public static Timestamp getCurTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 字符串转sql.date时间
     *
     * @param str yyyy-MM-dd 格式时间
     * @return
     */
    public static java.sql.Date getSqlDate(String str) {
        if (null != str && !"".equals(str)) {
            Date date = parse(str, DATE_SMALL_STR);
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    /**
     * 两日期的间隔天数
     *
     * @param strDateBegin 需要进行计较的日期1(yyyy-MM-dd)
     * @param strDateEnd 需要进行计较的日期2(yyyy-MM-dd)
     * @return 间隔天数（int）
     * @throws ParseException
     */
    public static int diffDay(String strDateBegin, String strDateEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_SMALL_STR);
        Date dateBegin = sdf.parse(strDateBegin);
        Date dateEnd = sdf.parse(strDateEnd);

        long milliSencods = dateEnd.getTime() - dateBegin.getTime();
        long intDiff = milliSencods / 1000 / (60 * 60 * 24);

        return (int) intDiff;
    }
    
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 两日期的间隔天数
     *
     * @param strDateBegin 需要进行计较的日期1(yyyy-MM-dd HH:mm:ss)
     * @param strDateEnd 需要进行计较的日期2(yyyy-MM-dd HH:mm:ss)
     * @return 间隔秒数（int）
     * @throws ParseException
     */
    public static long diffSecond(String strDateBegin, String strDateEnd) throws ParseException {
        return diffSecond(strDateBegin, strDateEnd, DATE_FULL_STR);
    }

    /**
     * 两日期的间隔天数
     *
     * @param strDateBegin 需要进行计较的日期1
     * @param strDateEnd 需要进行计较的日期2
     * @param formart  指定日期格式
     * @return 间隔秒数（int）
     * @throws ParseException
     */
    public static long diffSecond(String strDateBegin, String strDateEnd, String formart) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formart);
        Date dateBegin = sdf.parse(strDateBegin);
        Date dateEnd = sdf.parse(strDateEnd);

        long milliSencods = dateEnd.getTime() - dateBegin.getTime();
        long intDiff = milliSencods / 1000;
        return intDiff;
    }

    /**
     * 获取字符串格式日期的天
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @param format  格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static int getCurDayFromStr(String dateStr, String format) {
        int day = 0;
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date dtDate;
        try {
            dtDate = sf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtDate);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar = null;
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return day;
    }

    public static String formateDateStr(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FULL_STR);
        return sf.format(date);
    }

    public static long diffMinute(String strDateBegin, String strDateEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FULL_STR);
        Date dateBegin = sdf.parse(strDateBegin);
        Date dateEnd = sdf.parse(strDateEnd);

        long milliSencods = dateEnd.getTime() - dateBegin.getTime();
        long intDiff = milliSencods / 60000;
        return intDiff;
    }

    public static Date addMonths(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        date = c.getTime();
        return date;
    }

    public static Date getDate(String dateStr, String format) throws Exception {
        Date date = new Date();
        if (StringUtils.isEmpty(dateStr)) {
            return date;
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        date = formater.parse(dateStr);
        return date;
    }

    /**
     * 根据传入时间字符串长度来判断返回什么样的时间格式
     *
     * @param dateString
     * @return
     */
    public static String getFormat(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return "";
        }
        if (10 == dateString.length()) {
            return DATE_SMALL_STR;
        } else if (19 == dateString.length()) {
            return DATE_FULL_STR;
        }
        return "";
    }

    /**
     * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
     *
     * @param dtmDate   需要格式化的日期（java.util.Date）
     * @param strFormat 该日期的格式串
     * @return 格式化后的字符串（String）
     */
    public static String msFormatDateTime(Date dtmDate, String strFormat) {

        if (strFormat.equals("")){
            strFormat = DATE_FULL_STR;
        }
        SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

        return myFormat.format(dtmDate.getTime());
    }

    /**
     * 日期/时间格式化显示（年、月、日）
     *
     * @param dtmDate   需要格式化的日期（java.util.Date）
     * @param strFormat 该日期的格式串
     * @return 格式化后的字符串（String）
     */
    public static String msFormatDate(Date dtmDate, String strFormat) {

        if (strFormat.equals("")){
            strFormat = DATE_SMALL_STR;
        }

        SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

        return myFormat.format(dtmDate.getTime());
    }


    /**
     * 返回格式化的当前日期/时间
     *
     * @param strFormat 格式串
     * @return 当前日期/时间格式化后的字符串（String）
     */
    public static String getFormatCurrentDate(String strFormat) {
        return msFormatDateTime(new Date(), strFormat);
    }

    public static long getTimeDiff(Timestamp createTime, Timestamp startTimep) {
        long result = 0;
        result = createTime.getTime() - startTimep.getTime();
        return result;
    }

    public static String getMonthFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());
    }

    public static int dayCompare(Date fromDate, Date toDate) throws Exception {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        //只要年月
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DATE);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DATE);
        int year = toYear - fromYear;
        int month = year * 12 + (toMonth - fromMonth);
        int day = toDay - fromDay;
        if (day > 0) {
            month += 1;
        }
        return month;
    }

    /**
     * @return 6个月后的日期
     */
    public static Date getNextSixMonthTime() {
        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, +6);
            return c.getTime();
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return null;

    }

    /**
     * UTC时间转换
     *
     * @param UTCString
     * @return
     */
    public static Date UTCParse(String UTCString) {
        try {
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            return utcFormat.parse(UTCString);
        } catch (ParseException e) {
            new RuntimeException(e);
        }
        return null;
    }

    /**
     * 返回当前日期 yyyy-MM-dd格式
     *
     * @return
     */
    public static String getDtTmFomart(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String rs = "";
        try {
            rs = sdf.format(sf.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static Boolean isAfterNow(String str) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sf.parse(str + "000000");
            String nowStr = sf2.format(new Date()) + "000000";
            Date now = sf.parse(nowStr);

            return now.after(date);

        } catch (ParseException e) {
            //   e.printStackTrace();
            return null;
        }

    }



    /**
     *  获取上个月第一天
     * @return
     */
    public static Date getOneMonthPrior( ) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        System.out.println("上个月第一天："+time);
        return time;

    }
    /**
     *  获取上个月最后一天
     * @return
     */
    public static Date getOneMonthAfter( ) {

        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        calendar.set(Calendar.MINUTE, 59);
        //将秒至59
        calendar.set(Calendar.SECOND, 59);
        //将毫秒至999
        calendar.set(Calendar.MILLISECOND, 999);
        Date time = calendar.getTime();
        System.out.println("上个月最后一天："+ time);
        return  time;

    }

}
