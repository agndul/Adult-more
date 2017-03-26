package com.agadu.adultmore.timecheck.settings;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yoga on 2017-03-26.
 */
@Module
class TimeCheckSettingsModule {

    private final TimecheckSettingsContract.View mView;

    public TimeCheckSettingsModule(TimecheckSettingsContract.View view) {
        mView = view;
    }

    @Provides TimecheckSettingsContract.View provideTimeCheckSettingsView() {
        return mView;
    }

}
