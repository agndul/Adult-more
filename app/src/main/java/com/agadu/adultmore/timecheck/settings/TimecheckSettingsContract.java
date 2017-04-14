package com.agadu.adultmore.timecheck.settings;

import com.agadu.adultmore.timecheck.settings.adapter_settings.AdapterGeneralData;

import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-26.
 */

public interface TimecheckSettingsContract {

    interface View {
        void initAdapter(SettingsData settingsData);
    }

    interface Presenter {

        void updateSettings(Realm mTimeCheckRealm, AdapterGeneralData mAdapterGeneralData);

        void removeLast(Realm mTimeCheckRealm);

        void initView(Realm mTimeCheckRealm);
    }

}