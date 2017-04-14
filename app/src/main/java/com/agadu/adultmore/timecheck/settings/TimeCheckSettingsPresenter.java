package com.agadu.adultmore.timecheck.settings;

import com.agadu.adultmore.timecheck.settings.adapter_settings.AdapterGeneralData;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-26.
 */

public class TimeCheckSettingsPresenter implements TimecheckSettingsContract.Presenter {

    private TimecheckSettingsContract.View mView;
    private SettingsData settingsData;


    @Inject
    TimeCheckSettingsPresenter(TimecheckSettingsContract.View view) {
        mView = view;
    }

    @Override
    public void updateSettings(Realm mTimeCheckRealm, AdapterGeneralData mAdapterGeneralData) {

        mTimeCheckRealm.beginTransaction();
        settingsData = mTimeCheckRealm.createObject(SettingsData.class);
        settingsData.setStartTime(mAdapterGeneralData.getTimeData().getStartTime());
        settingsData.setMaxTime(mAdapterGeneralData.getTimeData().getMaxLateHours());
        settingsData.setDiffTime(mAdapterGeneralData.getTimeData().getDifferenceTime());
        settingsData.setInitialCharge(mAdapterGeneralData.getChargeData().getInitialCharge());
        settingsData.setDifference(mAdapterGeneralData.getChargeData().getDifference());
        settingsData.setMaxCharge(mAdapterGeneralData.getChargeData().getMaxCharge());
        settingsData.setLocation(mAdapterGeneralData.getLocationData().getLocation());
        settingsData.setDestLatitude(mAdapterGeneralData.getLocationData().getLatitude());
        settingsData.setDestLongitude(mAdapterGeneralData.getLocationData().getLongitude());
        mTimeCheckRealm.commitTransaction();
    }


    @Override
    public void removeLast(Realm mTimeCheckRealm){

        mTimeCheckRealm.beginTransaction();
        mTimeCheckRealm.where(SettingsData.class).findAll().deleteLastFromRealm();
        mTimeCheckRealm.commitTransaction();

    }
    @Override
    public void initView(Realm mTimeCheckRealm) {
        if(mTimeCheckRealm.where(SettingsData.class).findAll().isEmpty()) {
            mView.initAdapter(new SettingsData());
        }else {
            mView.initAdapter(mTimeCheckRealm.where(SettingsData.class).findAll().last());
        }
    }
}
