package com.agadu.adultmore.timecheck;

import android.location.LocationManager;

import java.util.List;

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
    interface SecondInnerView {
        void inject(TimeCheckContract.Presenter mTimeCheckPresenter, Realm mTimeCheckRealm);
        void initAdapter(List<TimeCheckObject> timecheckObj);

    }
    interface Presenter {
        void setLocationListener(LocationManager locationManager);
        void putTimeIntoDB(Realm mTimeCheckRealm, LocationManager locationManager, String excuse, boolean remote);
        void initScreenState(Realm mTimeCheckRealm);
        void removeLast(Realm mTimeCheckRealm);
        String getStartTime();
        void getHistoryData(Realm mTimeCheckRealm);

        }
}
