package com.agadu.adultmore.timecheck.settings.adapter_settings;

/**
 * Created by Yoga on 2017-04-13.
 */

public class LocationData extends AdapterData{

    private String location = "lalalla";
    private double latitude = 50.079465;
    private double longitude = 19.930948;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
