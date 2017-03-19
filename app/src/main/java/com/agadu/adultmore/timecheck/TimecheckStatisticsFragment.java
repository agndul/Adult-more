package com.agadu.adultmore.timecheck;

import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckStatisticsFragment extends Fragment implements TimeCheckContract.InnerView {

    private Realm mTimeCheckRealm;
    private TimeCheckContract.Presenter mTimeCheckPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_timecheck_settings, container, false);

        return rootView;
    }

    public void inject(TimeCheckContract.Presenter mTimeCheckPresenter, Realm mTimeCheckRealm, LocationManager mTimeCheckLocationManager) {
        mTimeCheckPresenter = mTimeCheckPresenter;
        mTimeCheckRealm = mTimeCheckRealm;

    }

    @Override
    public void setCurrentState(String excuse, boolean remote) {

    }

    public void historyResults() {
        RealmResults<TimeCheckObject> results1 =
                mTimeCheckRealm.where(TimeCheckObject.class).findAll();
        for (TimeCheckObject timeCheckObject : results1) {
            Log.i("time", ": " + timeCheckObject.getStartTime());
            Log.i("date", ": " + timeCheckObject.getStartDate());
        }

    }
}
