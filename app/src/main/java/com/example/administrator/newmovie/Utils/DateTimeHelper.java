package com.example.administrator.newmovie.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.example.administrator.newmovie.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangweiliang on 18/02/11.
 * Des:日期时间工具
 */
public class DateTimeHelper {
    public final static String SHORT_DATE_FORMAT = "YYYY-MM-dd";
    public static final DateTimeFormatter HIGH_RES_DATE_TIME_FORMATTER = DateTimeFormat.forPattern("Y-M-d H:m:s.S");
    public static final DateTimeFormatter LONG_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final java.text.DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static Pattern DATE_TIME_PATTERN =
            Pattern.compile("^(\\d{4})-(\\d{1,2})-(\\d{1,2})([\\s|T](\\d{1,2}):(\\d{1,2}):(\\d{1,2})\\.?(\\d*)Z?)?$");
    private final static String TAG = "DateTimeHelper";

    public static String getTimeStringFromSeconds(final int time) {
        final int SECONDS_OF_HOUR = 3600;
        final int SECONDS_OF_MINUTE = 60;

        if (time <= 0) {
            return "0:00";
        }

        if (time >= SECONDS_OF_HOUR) {
            //大于1小时
            return String.format("%d:%02d:%02d", time / SECONDS_OF_HOUR,
                    (time / SECONDS_OF_MINUTE) % SECONDS_OF_MINUTE,
                    time % SECONDS_OF_MINUTE);
        }

        return String.format("%d:%02d",
                time / SECONDS_OF_MINUTE,
                time % SECONDS_OF_MINUTE);
    }

    public static String getTimeStringFromMinute(final int time) {
        final int SECONDS_OF_HOUR = 3600;
        final int SECONDS_OF_MINUTE = 60;

        if (time <= 0) {
            return "0:00";
        }

        return String.format("%d:%02d", time / SECONDS_OF_HOUR,
                (time / SECONDS_OF_MINUTE) % SECONDS_OF_MINUTE);
    }

    /**
     * 把秒值转化为HH:mm:ss格式
     *
     * @param time
     * @return
     */
    public static String getHHMMSSFromSeconds(final long time) {
        final int SECONDS_OF_HOUR = 3600;
        final int SECONDS_OF_MINUTE = 60;

        if (time <= 0) {
            return "00:00:00";
        }

        //大于1小时
        return String.format("%02d:%02d:%02d", time / SECONDS_OF_HOUR,
                (time / SECONDS_OF_MINUTE) % SECONDS_OF_MINUTE,
                time % SECONDS_OF_MINUTE);

    }

    public static DateWithMicroSec parseDateTime(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }

        Matcher m = DATE_TIME_PATTERN.matcher(s);

        if (!m.matches()) {
            Log.w(TAG, "Wrong date format: " + s);
            return null;
        }
        try {
            int i = 1;

            final int year = Integer.parseInt(m.group(i++));
            final int month = Integer.parseInt(m.group(i++));
            final int day = Integer.parseInt(m.group(i++));
            int hour = 0;
            int min = 0;
            int sec = 0;
            int microseconds = 0;

            String t = m.group(i++);

            if (!TextUtils.isEmpty(t)) {
                t = m.group(i++);
                hour = TextUtils.isEmpty(t) ? 0 : Integer.parseInt(t);

                t = m.group(i++);
                min = TextUtils.isEmpty(t) ? 0 : Integer.parseInt(t);

                t = m.group(i++);
                sec = TextUtils.isEmpty(t) ? 0 : Integer.parseInt(t);

                t = m.group(i);
                microseconds = TextUtils.isEmpty(t) ? 0 : Integer.parseInt(t);
            }
            return new DateWithMicroSec(
                    new DateTime(year,
                            month,
                            day,
                            hour,
                            min,
                            sec,
                            microseconds / 1000,
                            DateTimeZone.getDefault()),
                    microseconds);
        } catch (Throwable t) {
            Log.w(TAG, "parseDateTime failed.", t);
        }

        return null;
    }

    public static boolean isSameDay(final DateTime a, final DateTime b) {
        if (a == null || b == null) {
            return false;
        }

        return (a.getYear() == b.getYear()) && (a.getDayOfYear() == b.getDayOfYear());
    }

    public static boolean isSameWeek(final DateTime a, final DateTime b) {
        if (a == null || b == null) {
            return false;
        }

        return (a.getYear() == b.getYear() && a.getWeekOfWeekyear() == b.getWeekOfWeekyear());
    }

    public static boolean isSameMonth(final DateTime a, final DateTime b) {
        if (a == null || b == null) {
            return false;
        }

        return (a.getYear() == b.getYear()) && (a.getMonthOfYear() == b.getMonthOfYear());
    }

    /*
            1）60秒内，显示为“刚刚”
            2）3600秒内，显示为“xx分钟前”
            3）今年内，显示为“月 - 日  时:分”
            4）今年之前，显示为“年 - 月 - 日”
        */
    public static String getRelativeDate(Context context, DateTime dateTime) {
        Duration duration = new Duration(dateTime, null);
        long diffSec = duration.getStandardSeconds();
        if (diffSec < 60) {
            // 刚刚
            return context.getResources().getString(R.string.a_moment_ago);
        } else if (diffSec < 3600) {
            // xx分钟前
            return duration.getStandardMinutes() + context.getResources().getString(R.string.minutes_before);
        } else {

            DateTime now = DateTime.now();
            if (now.getYear() == dateTime.getYear()) {
                return dateTime.toString("MM-dd HH:mm");
            }
            return dateTime.toString("YYYY-MM-dd");
        }
    }

    /*
        * 显示格式：
        * X 月 Y 日 时:分 ~ 时:分
        * */
    public static String getRelativeTimeDifferenceString(Context context,
                                                         DateTime timeBegin,
                                                         DateTime timeEnd) {
        String retStr;
        String timeFormat = timeBegin.toString("MM")
                + context.getResources().getString(R.string.unit_month)
                + timeBegin.toString("dd")
                + context.getResources().getString(R.string.unit_day)
                + " "
                + timeBegin.toString("HH:mm");
        retStr = String.format("%s ~ %s", timeFormat, timeEnd.toString("HH:mm"));
        return retStr;
    }

    /**
     * 获取两个时间点之间差值，显示为：xx:xx
     *
     * @param beginTime
     * @param endTime
     * @return 时间差值的绝对值
     */
    public static String getDurationTimeStringBetweenTwoDateTime(final DateTime beginTime,
                                                                 final DateTime endTime) {
        Duration duration = new Duration(beginTime, endTime);
        int minutes = (int) duration.getStandardMinutes();
        int seconds = (int) duration.getStandardSeconds() % 60;
        return getTimeStringByParameter(minutes, seconds);

    }

    public static String getTimeStringByParameter(int minutes, int seconds) {
        String ret = "";
        if (minutes == 0) {
            ret += "00";
        } else if (minutes < 10) {
            ret += "0";
            ret += String.valueOf(minutes);
        } else {
            ret += String.valueOf(minutes > 99 ? 99 : minutes);
        }
        ret += ":";

        if (seconds == 0) {
            ret += "00";
        } else if (seconds < 10) {
            ret += "0";
            ret += String.valueOf(seconds);
        } else {
            ret += String.valueOf(seconds);
        }
        return ret;
    }

    public static String getDayOffWeekString(Context context, int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return context.getString(R.string.day_1_of_week);
            case 2:
                return context.getString(R.string.day_2_of_week);
            case 3:
                return context.getString(R.string.day_3_of_week);
            case 4:
                return context.getString(R.string.day_4_of_week);
            case 5:
                return context.getString(R.string.day_5_of_week);
            case 6:
                return context.getString(R.string.day_6_of_week);
            case 7:
                return context.getString(R.string.day_7_of_week);
            default:
                return "";
        }
    }

    public static String getHourDurationByDate(final DateTime date, final int hour) {
        return String.format("%02d:00 ~ %02d:00", date.getHourOfDay(), date.getHourOfDay() + hour);
    }

    public static String toYearMMdd(final DateTime date) {
        return date != null ? date.toString("yyyy-MM-dd") : "";
    }

    public static int compareDate(String date1, String date2) {
        try {
            Date dt1 = DATE_FORMAT.parse(date1);
            Date dt2 =  DATE_FORMAT.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

}
