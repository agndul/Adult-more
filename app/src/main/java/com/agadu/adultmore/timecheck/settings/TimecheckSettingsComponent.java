package com.agadu.adultmore.timecheck.settings;

import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;
import com.agadu.adultmore.general.scope.UserScope;

import dagger.Component;

/**
 * Created by Yoga on 2017-03-05.
 */
@UserScope
@Component(modules = {LocationModule.class, DatabaseModule.class, TimeCheckSettingsModule.class})
public interface TimecheckSettingsComponent {

    void inject(TimecheckSettingsActivity activity);
}
