package com.agadu.adultmore.timecheck.settings.adapter_settings;

/**
 * Created by Yoga on 2017-04-13.
 */

public class ChargeData {

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

    private float initialCharge;
    private float difference;
    private float maxCharge;


}
