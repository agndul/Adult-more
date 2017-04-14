package com.agadu.adultmore.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yoga on 2017-03-19.
 */

public class TimeFormatsHelper {

    private static String timeFormat = "HH:mm:ss";
    private static String dateDbFormat = "yyyy-MM-dd";
    private static String datePreviewFormat = "dd MMM 'at' HH:mm";

    public static String returnPreviewDate(long time){
        return new SimpleDateFormat(datePreviewFormat).format(time);
    }
    public static String returnDBDate(Date time){
        return new SimpleDateFormat(dateDbFormat).format(time);
    }
    public static String returnDBDate(long time){
        return new SimpleDateFormat(dateDbFormat).format(time);
    }
    public static String returnDBTime(long time){
        return new SimpleDateFormat(timeFormat).format(time);
    }
    public static int returnMins(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Date mins = sdf.parse(time);
        long milisecs = mins.getTime();
        return (int) milisecs/1000;
    }

    public static float returnMinsDiff(String time, String timeSettings) throws ParseException {
        float minDiff = getTimeDifference(time, timeSettings)/1000/60;
        return minDiff;
    }

    public static long getTimeDifference(String startTime, String timeSettings) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Date timeOne = sdf.parse(startTime);
        Date timeTwo = sdf.parse(timeSettings);
        long timeDiff = timeOne.getTime() - timeTwo.getTime();
        System.out.println("difference:" + timeDiff);   // difference: 0
        return timeDiff;
    }
}

