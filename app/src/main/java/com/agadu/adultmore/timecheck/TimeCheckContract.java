package com.agadu.adultmore.timecheck;

import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;

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
        boolean checkDestLocation(Location lastKnownLocation);

        void putTimeIntoDb(Realm mTimeCheckRealm, @Nullable Location lastKnownLocation, String excuse, boolean remote);
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
