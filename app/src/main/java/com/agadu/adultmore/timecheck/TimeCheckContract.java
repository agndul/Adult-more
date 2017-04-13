package com.agadu.adultmore.timecheck;

import android.location.LocationManager;

import com.agadu.adultmore.timecheck.settings.SettingsData;

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
        void initAdapter(List<TimeCheckObj> timecheckObj, SettingsData settingsData);

        void refreshAdapter();
    }
    interface Presenter {
        void setLocationListener(LocationManager locationManager);

        boolean checkDestLocation(LocationManager mTimeCheckLocationManager);

        void putTimeIntoDb(Realm mTimeCheckRealm, LocationManager locationManager, String excuse, boolean remote);
        void initScreenState(Realm mTimeCheckRealm);

        void putSettingsIntoDb(Realm mTimeCheckRealm);

        void removeLast(Realm mTimeCheckRealm);
        String getStartTime();
        void getHistoryData(Realm mTimeCheckRealm);

        TimeCheckObj getStats(int layoutPosition);
        void setExcuseAccepted(Realm mTimeCheckRealm, int layoutPosition, boolean b);

        void refresh();

    }
}
