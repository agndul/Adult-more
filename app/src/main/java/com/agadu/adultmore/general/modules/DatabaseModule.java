package com.agadu.adultmore.general.modules;

import com.agadu.adultmore.general.services.RealmService;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-05.
 */

@Module
public class DatabaseModule {

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}
