package com.kure.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Title:
 * Description: 时间工具类
 *
 * @auther: heshenxing
 * @Date: 2018/8/30 14:17
 */
public final class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);


    public static final ThreadLocal<SimpleDateFormat> YYYYMMDD_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static final ThreadLocal<SimpleDateFormat> HHMMSS = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss"));

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMM = "yyyyMM";

    public static final String DD = "dd";


    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 得到当前日期的整数形式,yyyyMMdd。
     *
     * @return 当前日期的整数形式
     */
    public static int getDate() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * <p>Title: 读取当前的日期+时间</p>
     * Description: yyyy-MM-dd HH:mm:ss
     *
     * @author HuangTao
     * @version 1.0
     * @since 2018/9/20 16:28
     **/
    public static String getDateTimeStr() {
        return YYYYMMDDHHMMSS_FORMAT.get().format(Calendar.getInstance().getTime());
    }

    /**
     * <p>Title: Date类型(日期+时间) 转换为 String 类型</p>
     * Description:
     *
     * @param dateTime
     * @return String
     * @author HuangTao
     * @version 1.0
     * @since 2018/9/27 15:05
     **/
    public static String getDateTimeStr(Date dateTime) {
        return YYYYMMDDHHMMSS_FORMAT.get().format(dateTime);
    }


    /**
     * 指定增加类型和数目的,当前日期之后的某个日期
     *
     * @param field  时间类型,如年、月、日、时、分、秒
     * @param amount 时间数目
     * @return 日期
     */
    public static Calendar add(int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.set(field, cal.get(field) + amount);
        return cal;
    }

    /**
     * 格式化输出
     *
     * @param cal     日期对象
     * @param sformat 输出格式
     * @return 字符串格式日期
     */
    public static String format(Calendar cal, String sformat) {
        // yyyyMMddHHmmss
        SimpleDateFormat dateFormat;
        try {
            dateFormat = new SimpleDateFormat(sformat);
        } catch (IllegalArgumentException e) {
            dateFormat = YYYYMMDDHHMMSS_FORMAT.get();
        }
        return dateFormat.format(cal == null ? Calendar.getInstance().getTime()
                : cal.getTime());
    }

    /**
     * 得到当前时间的java.util.Date类型的日期
     *
     * @return 日期
     */
    public static Date getNowDate() {
        Calendar now = Calendar.getInstance();
        return new GregorianCalendar(now.get(Calendar.YEAR), now
                .get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).getTime();
    }


    /**
     * 返回时间 格式:时/分/秒 hhnnss
     *
     * @return 时间的整数形式
     */
    public static int getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        return hour * 10000 + minute * 100 + second;
    }


    /**
     * 转换日期 20080101 -> Date
     *
     * @param dateStr 日期字符串
     * @param format  日期的字符串格式如yyyyMMdd
     * @return Date 日期
     */
    public static Date getUtilDate(String dateStr, String format) {
        String temp = format;
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        try {
            temp = temp == null ? YYYY_MM_DDHHMMSS : temp;
            SimpleDateFormat sdf = new SimpleDateFormat(temp);
            return sdf.parse(dateStr);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return  null;
    }

    /**
     * 按yyyyMMdd G:i:s格式比较两个日期的大小
     *
     * @param date1 比较的第一个日期
     * @param date2 比较的第二个日期
     * @return 两个日期相等返回0, date1大于date2返回1, date1小于date2返回-1.
     */
    public static int compareTime(Date date1, Date date2) {
        long intDate1 = date1.getTime();
        long intDate2 = date2.getTime();
        return intDate1 >= intDate2 ? (intDate1 == intDate2 ? 0 : 1) : -1;
    }

    /**
     * 返回HH:mm:ss类型时间
     *
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return HHMMSS.get().format(date);
    }


    /**
     * <p>Title: 根据传入日期生成指定天数后的日期</p>
     * Description:默认两种字符串日期形式(yyyyMMdd/yyyyMMdd hh:mm:ss)
     *
     * @param date 传入日期
     * @param days 指定天数
     * @return 计算后的日期
     * @author HuangTao
     * @version 1.0
     * @since 2018/11/2 11:47
     **/
    public static String appointByDays(String date, int days) {
        return appointByCaLType(date, days, Calendar.DATE);
    }

    /**
     * <p>Title </p>
     * <p>Description 根据传入日期，得到days周期后的日期,返回的字符串形式为yyyyMMdd</p>
     *
     * @param date    日期(只能是yyyyMMdd或yyyy-MM-dd hh:ss:mm)
     * @param days    周期数
     * @param calType 周期类型
     * @return String yyyyMMdd
     * @author LiBinbing
     * @date 2018/11/19 14:27
     * @version 1.0
     **/
    public static String appointByCaLType(String date, int days, int calType) {
        Calendar cal = Calendar.getInstance();
        if (date.length() == 8) {
            cal.setTime(getUtilDate(date, YYYYMMDD));
        } else {
            cal.setTime(getUtilDate(date, YYYY_MM_DDHHMMSS));
        }
        cal.add(calType, days);
        return  YYYYMMDD_FORMAT.get().format(cal.getTime());
    }

    /**
     * 根据传入日期生成指定数周后的日期
     *
     * @param date  传入日期
     * @param weeks 指定天数
     * @return 日期
     */
    public static String appointByWeek(String date, int weeks) {
        return appointByCaLType(date, weeks, Calendar.WEDNESDAY);
    }

    /**
     * 根据传入日期生成指定数月后的日期
     *
     * @param date   传入日期
     * @param months 指定天数
     * @return 日期
     */
    public static String appointByMonth(String date, int months) {
        return appointByCaLType(date, months, Calendar.MONTH);
    }

    /**
     * 获取本周或下周某日的日期
     * <p>
     * 今天为周一，传入weekDay为3，返回本周三日期；
     * <p>
     * 今天为周四，传入3，返回下周三日期；
     * <p>
     * 今天为周三，传入3，返回下周三日期；
     *
     * @param date    - 系统日期
     * @param weekDay - 周几
     * @return yyyyMMdd
     * @author TANGYE
     */
    public static String getNextWeekDay(Date date, int weekDay) {
        int dayPlus = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < weekDay) {
            dayPlus = weekDay - dayOfWeek;
        } else {
            dayPlus = 1 - dayOfWeek + 6 + weekDay;
        }
        cal.add(GregorianCalendar.DATE, dayPlus);
        return YYYYMMDD_FORMAT.get().format(cal.getTime());
    }

    /**
     * 获取当月或下月某号的日期
     * <p>
     * Description：如果date号数(如：20160515，则为15号)小于day时，返回当月的指定几号的日期，或者返回下月的指定日期
     *
     * @param date -
     *             系统日期
     * @param day  -
     *             某月的几号
     * @return yyyyMMdd
     * @author TANGYE
     */
    public static String getNextMonthDay(Date date, int day) {
        String yyyyMM = getDateStr(date, YYYYMM);
        String dateStr = getDateStr(date, DD);
        int dd = Integer.parseInt(dateStr);
        if (day > dd) {
            String month = yyyyMM + day;
            Calendar cal = Calendar.getInstance();
            cal.setTime(getUtilDate(month, YYYYMMDD));
            return YYYYMMDD_FORMAT.get().format(cal.getTime());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getUtilDate(yyyyMM + day, YYYYMMDD));
            cal.add(Calendar.MONTH, 1);
            return YYYYMMDD_FORMAT.get().format(cal.getTime());
        }
    }

    public static Calendar dateParseToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Description: 根据给定的format类型返回日期类型
     *
     * @param
     * @return
     * @auther HESHENXING
     * @date 2018/9/13 19:14
     */
    public static String getDateStr(Date date, String format) {
        if (null == date || StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * <p>Title: 简化日期型，去掉时间显示</p>
     * Description:
     *
     * @param date
     * @return String
     * @author HuangTao
     * @version 1.0
     * @since 2018/10/31 11:07
     **/
    public static String getSimpleDate(Date date) {
        if (date == null) {
            return "";
        }
        return YYYY_MM_DD_FORMAT.get().format(date);
    }

    /**
     * <p>Title: 只取年月日的时间</p>
     * Description:
     *
     * @param date
     * @return Date
     * @author HuangTao
     * @version 1.0
     * @since 2018/10/31 11:07
     **/
    public static Date getEasyDate(Date date) {
        return getUtilDate(getSimpleDate(date), YYYY_MM_DD);
    }


    /**
     * <p>Title: 计算两日之间的间隔天数</p>
     * Description:
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return
     * @author HuangTao
     * @version 1.0
     * @since 2018/10/31 11:00
     **/
    public static int getBetweenDate(Date startDay, Date endDay) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(startDay);
        endCal.setTime(endDay);
        return (int) ((endCal.getTime().getTime() - startCal.getTime()
                .getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * <p>Title </p>
     * <p>Description 两个时刻之间的相差的秒数,只包含时分秒，不包括年月日 </p>
     * interval = expectTime (-) time
     *
     * @param expectTime hh24miss
     * @param time       hh24miss
     * @return
     * @author LiBinbing
     * @date 2018/11/8 16:22
     * @version 1.0
     **/
    public static long intervalBetweenTime(int expectTime, int time) {

        Calendar calendarE = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendarE.set(Calendar.HOUR, expectTime / 10000);
        calendarE.set(Calendar.MINUTE, (expectTime / 100) % 100);
        calendarE.set(Calendar.SECOND, expectTime % 100);
        calendar.set(Calendar.HOUR, time / 10000);
        calendar.set(Calendar.MINUTE, (time / 100) % 100);
        calendar.set(Calendar.SECOND, time % 100);

        return (calendarE.getTimeInMillis() - calendar.getTimeInMillis()) / 1000;
    }

    /**
     * <p>Title </p>
     * <p>Description 比较两个Date类型相差的数值 interval = firstDate - secondDate</p>
     *
     * @param firstDate
     * @param secondDate
     * @param type       数值单位 eg.Calendar.HOUR
     * @return
     * @author LiBinbing
     * @date 2018/12/11 10:51
     * @version 1.0
     **/
    public static int intervalBetweenDate(Date firstDate, Date secondDate, int type) {
        Calendar fCalendar = Calendar.getInstance();
        Calendar sCalendar = Calendar.getInstance();
        fCalendar.setTime(firstDate);
        sCalendar.setTime(secondDate);
        return fCalendar.get(type) - sCalendar.get(type);
    }

    /**
     * <p>Title: 指定时间点的向后推移指定的时间</p>
     * Description:
     * @param currTime 指定时间
     * @param spaceTime 推移时间
     * @return 推移后的时间点
     * @author HuangTao
     * @version 1.0
     * @since 2019/2/14 11:28
     **/
    public static String convertTime(String currTime,long spaceTime){
        String convertAfterTime = "";
        try {
            long st = spaceTime * 1000;
            Date date = HHMMSS.get().parse(currTime);
            long afterTime = date.getTime() + st;
            convertAfterTime = HHMMSS.get().format(afterTime);
        } catch (Exception e) {
            convertAfterTime = currTime;
            logger.error(e.getMessage(),e);
        }
        return convertAfterTime;
    }

}