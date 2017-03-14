package com.agadu.adultmore.general.services;

import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-05.
 */

public class RealmService {
    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    //other methods
}