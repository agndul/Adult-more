package com.agadu.adultmore.dashboard;

import com.agadu.adultmore.general.components.ApplicationComponent;
import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;
import com.agadu.adultmore.general.scope.UserScope;
import com.agadu.adultmore.timecheck.TimeCheckModule;

import dagger.Component;

/**
 * Created by Yoga on 2017-03-05.
 */
@UserScope
@Component(modules = {DashboardModule.class, DatabaseModule.class}, dependencies = ApplicationComponent.class)

public interface DashboardComponent {
    void inject(DashboardActivity activity);
    // to update the fields in your activities
}