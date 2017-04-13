package com.agadu.adultmore.timecheck.settings.adapter_settings;

/**
 * Created by Yoga on 2017-04-13.
 */

public class TimeData extends AdapterData{

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDifferenceTime() {
        return differenceTime;
    }

    public void setDifferenceTime(String differenceTime) {
        this.differenceTime = differenceTime;
    }

    public String getMaxLateHours() {
        return maxLateHours;
    }

    public void setMaxLateHours(String maxLateHours) {
        this.maxLateHours = maxLateHours;
    }

    private String startTime;

    private String differenceTime;

    private String maxLateHours;

}
