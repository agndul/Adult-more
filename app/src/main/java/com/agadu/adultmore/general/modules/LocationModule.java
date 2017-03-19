package com.agadu.adultmore.general.modules;

import android.content.Context;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yoga on 2017-03-19.
 */
@Module
public class LocationModule {

    private final Context context;

    public LocationModule(Context context) {
        this.context = context;
    }
    @Provides
    LocationManager provideLocationManager() {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
