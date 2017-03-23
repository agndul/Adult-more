package com.agadu.adultmore.helpers;

/**
 * Created by Yoga on 2017-03-23.
 */

public class DistanceHelper {

    public static double getDistance(double lon, double lat, double destLon, double destLat) {
        double distanceSquare = Math.pow(destLon-lon,2) + Math.pow(destLat-lat,2);

        return Math.sqrt(distanceSquare);
    }
}
