package com.agadu.adultmore.helpers;

import com.agadu.adultmore.timecheck.settings.SettingsData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yoga on 2017-03-19.
 */

public class TimeFormatsHelper {

    private static String minsFormat = "HH:mm";
    private static String hoursFormat = "HH";
    private static String timeFormat = "HH:mm:ss";
    private static String dateDbFormat = "yyyy-MM-dd";
    private static String datePreviewFormat = "dd MMM 'at' HH:mm";
    private static SimpleDateFormat formatterMins = new SimpleDateFormat(minsFormat);
    private static SimpleDateFormat formatterHours = new SimpleDateFormat(hoursFormat);
    private static SimpleDateFormat formatterTime = new SimpleDateFormat(timeFormat);
    private static SimpleDateFormat formatterDate = new SimpleDateFormat(dateDbFormat);
    private static SimpleDateFormat formatterDatePreview = new SimpleDateFormat(datePreviewFormat);

    public static String returnPreviewDate(long time){
        return formatterDatePreview.format(time);
    }
    public static String returnDBDate(Date time){
        return formatterDate.format(time);
    }
    public static String returnDBDate(long time){
        return formatterDate.format(time);
    }
    public static String returnDBTime(long time){
        return formatterTime.format(time);
    }
    public static float returnMinsDiff(String time) throws ParseException {
        float minDiff = getTimeDifference(time)/1000/60;
        return minDiff;
    }

    public static long getTimeDifference(String startTime) throws ParseException {
        Date timeOne = formatterTime.parse(startTime);
        Date timeTwo = formatterTime.parse(SettingsData.startTime);
        long timeDiff = timeOne.getTime() - timeTwo.getTime();
        System.out.println("difference:" + timeDiff);   // difference: 0
        return timeDiff;
    }
}

