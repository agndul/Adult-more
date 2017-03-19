package com.agadu.adultmore.timecheck;

import android.location.LocationManager;

import io.realm.Realm;

/**
 * Created by Yoga on 2016-10-30.
 */

public interface TimeCheckContract {

    interface OuterView {
    }

    interface InnerView {
        void inject(TimeCheckContract.Presenter mPresenterm, Realm reamldb, LocationManager locationManager);

        void setCurrentState(String excuse, boolean remote);
    }

    interface Presenter {
        void setLocationListener(LocationManager locationManager);
        void putTimeIntoDB(Realm mTimeCheckRealm, LocationManager locationManager, String excuse, boolean remote);
        void initScreenState(Realm mTimeCheckRealm);
        void removeLast(Realm mTimeCheckRealm);
        String getStartTime();
    }
}
