package com.agadu.adultmore.timecheck;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.agadu.adultmore.helpers.DistanceHelper;
import com.agadu.adultmore.helpers.TimeFormatsHelper;

import java.util.Calendar;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.agadu.adultmore.timecheck.settings.SettingsData.DEST_LAT;
import static com.agadu.adultmore.timecheck.settings.SettingsData.DEST_LON;
import static com.agadu.adultmore.timecheck.settings.SettingsData.RADIUS;

/**
 * Created by Yoga on 2016-09-11.
 */
public class TimeCheckPresenter implements TimeCheckContract.Presenter{
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //todo: show error if disabled, hide error if enabled

        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
            //todo: show error (dialog with expl)
        }
    };
    private String locationProvider = LocationManager.NETWORK_PROVIDER;

    private TimeCheckContract.OuterView mView;
    private TimeCheckContract.InnerView mInnerView;
    private TimeCheckContract.SecondInnerView mSecondInnerView;
    RealmResults<TimeCheckObject> results;
    private String startTime, startDate;

    @Inject
    TimeCheckPresenter(TimeCheckContract.OuterView view, TimeCheckContract.InnerView innerView, TimeCheckContract.SecondInnerView secondInnerView) {
        mView = view;
        mInnerView = innerView;
        mSecondInnerView = secondInnerView;

    }
    @Override
    public void setLocationListener(LocationManager locationManager){
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }


    @Override
    public void initScreenState(Realm mTimeCheckRealm){
        startDate = new TimeFormatsHelper().returnDBDate(Calendar.getInstance().getTime());

        if(!mTimeCheckRealm.where(TimeCheckObject.class).findAll().isEmpty()) {
            TimeCheckObject lastResult =
                    mTimeCheckRealm.where(TimeCheckObject.class).findAll().last();

            String currentStartDate = lastResult.getStartDate();
            if (startDate.equals(currentStartDate)) {
                startTime = lastResult.getStartTime();
                mInnerView.setCurrentState(lastResult.getExcuse(), lastResult.isRemote());
            }
        }
    }
    @Override
    public boolean checkDestLocation(LocationManager locationManager) {
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if(new DistanceHelper().getDistance(lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude(), DEST_LON, DEST_LAT) > RADIUS)
            return false;
        else return true;
    }
    @Override
    public void putTimeIntoDB(Realm mTimeCheckRealm, LocationManager locationManager, String excuse, boolean remote){
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        mTimeCheckRealm.beginTransaction();

        TimeCheckObject timeCheckObject = mTimeCheckRealm.createObject(TimeCheckObject.class);
        startDate = new TimeFormatsHelper().returnDBDate(lastKnownLocation.getTime());
        startTime= new TimeFormatsHelper().returnDBTime(lastKnownLocation.getTime());
        timeCheckObject.setStartDate(startDate);
        timeCheckObject.setStartTime(startTime);
        timeCheckObject.setTime(lastKnownLocation.getTime());
        timeCheckObject.setLatitude(lastKnownLocation.getLatitude());
        timeCheckObject.setLongitude(lastKnownLocation.getLongitude());
        timeCheckObject.setExcuse(excuse);
        timeCheckObject.setRemote(remote);
        timeCheckObject.setUserId(0);

        mTimeCheckRealm.commitTransaction();

    }
    @Override
    public void removeLast(Realm mTimeCheckRealm){

        mTimeCheckRealm.beginTransaction();
        mTimeCheckRealm.where(TimeCheckObject.class).findAll().last().deleteFromRealm();
        mTimeCheckRealm.commitTransaction();

    }

    @Override
    public String getStartTime(){
        return startTime;
    }

    @Override
    public void getHistoryData(Realm mTimeCheckRealm) {

        results = mTimeCheckRealm.where(TimeCheckObject.class).findAll().sort("time", Sort.DESCENDING);

        mSecondInnerView.initAdapter(results);
    }

    @Override
    public TimeCheckObject getStats(int position){
        return results.get(position);
    }
}
