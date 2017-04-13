package com.agadu.adultmore.timecheck.settings.adapter_settings;

/**
 * Created by Yoga on 2017-04-13.
 */

public class AdapterGeneralData {
    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public TimeData getTimeData() {
        return timeData;
    }

    public void setTimeData(TimeData timeData) {
        this.timeData = timeData;
    }

    public ChargeData getChargeData() {
        return chargeData;
    }

    public void setChargeData(ChargeData chargeData) {
        this.chargeData = chargeData;
    }

    private LocationData locationData;
    private TimeData timeData;
    private ChargeData chargeData;

}
