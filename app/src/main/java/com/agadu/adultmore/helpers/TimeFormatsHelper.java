package com.agadu.adultmore.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yoga on 2017-03-19.
 */

public class TimeFormatsHelper {
    public static final float MINS_IN_HOUR = 60;

    private static String timeFormat = "HH:mm:ss";
    private static String timeInputFormat = "HH:mm";

    private static String dateDbFormat = "yyyy-MM-dd";
    private static String datePreviewFormat = "dd MMM 'at' HH:mm";
    private static Locale locale = Locale.UK;

    public static String returnPreviewDate(long time){
        return new SimpleDateFormat(datePreviewFormat, locale).format(time);
    }
    public static String returnDBDate(Date time){
        return new SimpleDateFormat(dateDbFormat, locale).format(time);
    }
    public static String returnDBDate(long time){
        return new SimpleDateFormat(dateDbFormat, locale).format(time);
    }
    public static String returnDBTime(long time){
        return new SimpleDateFormat(timeFormat, locale).format(time);
    }

    public static float returnMinsDiff(String time, String timeSettings) throws ParseException {
        float minDiff = getTimeDifference(time, timeSettings)/1000/60;
        return minDiff;
    }

    public static long getTimeDifference(String startTime, String timeSettings) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, locale);
        SimpleDateFormat sdfInput = new SimpleDateFormat(timeInputFormat, locale);
        Date timeOne = sdf.parse(startTime);
        Date timeTwo = sdfInput.parse(timeSettings);
        long timeDiff = timeOne.getTime() - timeTwo.getTime();
        System.out.println("difference:" + timeDiff);   // difference: 0
        return timeDiff;
    }
}

