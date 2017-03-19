package com.agadu.adultmore.timecheck;

import io.realm.RealmObject;

/**
 * Created by Yoga on 2016-09-11.
 */
public class TimeCheckObject extends RealmObject{


    public TimeCheckObject (){}
    private String startDate;
    private String startTime;
    private int userId;
    private String excuse;
    private boolean remote;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double latitude;
    private double longitude;

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

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }


}
