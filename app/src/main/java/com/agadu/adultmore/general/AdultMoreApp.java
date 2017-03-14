package com.agadu.adultmore.general;

import android.app.Application;

import com.agadu.adultmore.general.components.ApplicationComponent;
import com.agadu.adultmore.general.components.DaggerApplicationComponent;
import com.agadu.adultmore.general.modules.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.agadu.adultmore.general.Constants.DB_FILE_NAME_RM;

/**
 * Created by Yoga on 2016-12-11.
 */

public class AdultMoreApp extends Application {

    ApplicationComponent applicationComponent;
    RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();

    }

    private void initRealmConfiguration() {

        Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder()
                .name(DB_FILE_NAME_RM)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }
}
