package com.agadu.adultmore.timecheck;

import io.realm.RealmObject;

/**
 * Created by Yoga on 2016-09-11.
 */
public class TimeCheckObject extends RealmObject{

    private String startDate;
    private String startTime;
    private int userId;
    public TimeCheckObject (){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
