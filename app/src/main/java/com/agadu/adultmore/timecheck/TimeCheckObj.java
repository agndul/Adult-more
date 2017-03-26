package com.agadu.adultmore.timecheck;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Yoga on 2017-03-26.
 */

public class TimeCheckObj extends RealmObject {

    public TimeCheckObj(){}
    @SerializedName("time")
    private long time;
    private String startDate;
    private String startTime;
    private int userId;
    private String excuse;
    private boolean remote;
    private double latitude;
    private double longitude;
    @Nullable private Boolean isExcuseAccepted;
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
    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }

    public void setExcuseAccepted( @Nullable Boolean isAccepted) {
        isExcuseAccepted = isAccepted;
    }
    @Nullable
    public Boolean isExcuseAccepted() {
        return isExcuseAccepted;
    }
}