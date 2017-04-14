package com.agadu.adultmore.timecheck;

import android.location.Location;
import android.support.annotation.Nullable;

import com.agadu.adultmore.helpers.DistanceHelper;
import com.agadu.adultmore.helpers.TimeFormatsHelper;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import java.util.Calendar;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.agadu.adultmore.timecheck.settings.SettingsData.RADIUS;

/**
 * Created by Yoga on 2016-09-11.
 */
public class TimeCheckPresenter implements TimeCheckContract.Presenter {


    private TimeCheckContract.OuterView mView;
    private TimeCheckContract.InnerView mInnerView;
    private TimeCheckContract.SecondInnerView mSecondInnerView;
    private RealmResults<TimeCheckObj> results;
    private String startTime, startDate;
    private SettingsData settingsData;

    @Inject
    TimeCheckPresenter(TimeCheckContract.OuterView view, TimeCheckContract.InnerView innerView, TimeCheckContract.SecondInnerView secondInnerView) {
        mView = view;
        mInnerView = innerView;
        mSecondInnerView = secondInnerView;

    }

    @Override
    public void initScreenState(Realm mTimeCheckRealm){
        startDate = TimeFormatsHelper.returnDBDate(Calendar.getInstance().getTime());

        if(!mTimeCheckRealm.where(SettingsData.class).findAll().isEmpty()) {
            settingsData =
                    mTimeCheckRealm.where(SettingsData.class).findAll().last();
        }else {
            putSettingsIntoDb(mTimeCheckRealm);
            settingsData =
                    mTimeCheckRealm.where(SettingsData.class).findAll().last();
        }

        if(!mTimeCheckRealm.where(TimeCheckObj.class).findAll().isEmpty()) {
            TimeCheckObj lastResult =
                    mTimeCheckRealm.where(TimeCheckObj.class).findAll().last();

            String currentStartDate = lastResult.getStartDate();
            if (startDate.equals(currentStartDate)) {
                startTime = lastResult.getStartTime();
                mInnerView.setCurrentState(lastResult.getExcuse(), lastResult.isRemote());
            }
        }
    }
    @Override
    public boolean checkDestLocation(Location lastKnownLocation) {
        if(lastKnownLocation==null)
            return false;
        if(DistanceHelper.getDistance(lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude(), settingsData.getDestLongitude(), settingsData.getDestLatitude()) > RADIUS)
            return false;
        else return true;
    }
    @Override
    public void putTimeIntoDb(Realm mTimeCheckRealm, @Nullable Location lastKnownLocation, String excuse, boolean remote){

        mTimeCheckRealm.beginTransaction();

        TimeCheckObj timeCheckObject = mTimeCheckRealm.createObject(TimeCheckObj.class);
        startDate = TimeFormatsHelper.returnDBDate(lastKnownLocation!=null ? lastKnownLocation.getTime() : Calendar.getInstance().getTime().getDate());
        startTime= TimeFormatsHelper.returnDBTime(lastKnownLocation!=null ? lastKnownLocation.getTime() : Calendar.getInstance().getTime().getTime());
        timeCheckObject.setStartDate(startDate);
        timeCheckObject.setStartTime(startTime);
        timeCheckObject.setTime(lastKnownLocation!=null ? lastKnownLocation.getTime() : Calendar.getInstance().getTime().getTime());
        timeCheckObject.setLatitude(lastKnownLocation!=null ? lastKnownLocation.getLatitude() : 0);
        timeCheckObject.setLongitude(lastKnownLocation!=null ? lastKnownLocation.getLongitude() : 0);
        timeCheckObject.setExcuse(excuse);
        timeCheckObject.setRemote(remote);
        timeCheckObject.setUserId(0);

        mTimeCheckRealm.commitTransaction();

    }

    @Override
    public void putSettingsIntoDb(Realm mTimeCheckRealm){

        mTimeCheckRealm.beginTransaction();
        settingsData = mTimeCheckRealm.createObject(SettingsData.class);
        mTimeCheckRealm.commitTransaction();

    }

    @Override
    public void removeLast(Realm mTimeCheckRealm){

        mTimeCheckRealm.beginTransaction();
        mTimeCheckRealm.where(TimeCheckObj.class).findAll().deleteLastFromRealm();
        mTimeCheckRealm.commitTransaction();

    }

    @Override
    public String getStartTime(){
        return startTime;
    }

    @Override
    public void getHistoryData(Realm mTimeCheckRealm) {

        results = mTimeCheckRealm.where(TimeCheckObj.class).findAll().sort("time", Sort.DESCENDING);
        mSecondInnerView.initAdapter(results, settingsData);
    }

    @Override
    public TimeCheckObj getStats(int position){
        return results.get(position);
    }

    @Override
    public void setExcuseAccepted(Realm mTimeCheckRealm, int layoutPosition, boolean accepted) {

        mTimeCheckRealm.beginTransaction();
        mTimeCheckRealm.where(TimeCheckObj.class).findAll().get(layoutPosition).setExcuseAccepted(accepted);
        mTimeCheckRealm.commitTransaction();
        refresh();
    }

    @Override
    public void refresh(){
        mSecondInnerView.refreshAdapter();

    }
}
