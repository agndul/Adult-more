package com.agadu.adultmore.timecheck.settings;

import io.realm.RealmObject;

/**
 * Created by Yoga on 2017-03-19.
 */

public class SettingsData extends RealmObject {
    public static double RADIUS = 0.00045;
    public static float SPECIAL_CHARGE = 0.5f;

    private String startTime = "8:00";
    private float diffTime = 15f;
    private float maxTime = 1.5f;
    private float initialCharge = 2f;
    private float difference = 1f;
    private float maxCharge = 10f;

    //todo: fix location
    private double destLatitude = 50.079465;
    private double destLongitude = 19.930948;
    private String location = "street name";


    public float getInitialCharge() {
        return initialCharge;
    }

    public void setInitialCharge(float initialCharge) {
        this.initialCharge = initialCharge;
    }

    public float getDifference() {
        return difference;
    }

    public void setDifference(float difference) {
        this.difference = difference;
    }

    public float getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(float maxCharge) {
        this.maxCharge = maxCharge;
    }

    public double getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(double destLatitude) {
        this.destLatitude = destLatitude;
    }

    public double getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(double destLongitude) {
        this.destLongitude = destLongitude;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public float getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(float diffTime) {
        this.diffTime = diffTime;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
