package com.agadu.adultmore.helpers;

import com.agadu.adultmore.timecheck.settings.SettingsData;

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
    public static float returnMinsDiff(String time) throws ParseException {
        float minDiff = getTimeDifference(time)/1000/60;
        return minDiff;
    }

    public static long getTimeDifference(String startTime) throws ParseException {
        Date timeOne = new SimpleDateFormat(timeFormat).parse(startTime);
        Date timeTwo = new SimpleDateFormat(timeFormat).parse(SettingsData.START_TIME);
        long timeDiff = timeOne.getTime() - timeTwo.getTime();
        System.out.println("difference:" + timeDiff);   // difference: 0
        return timeDiff;
    }
}

