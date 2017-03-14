package com.agadu.adultmore.dashboard;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yoga on 2016-10-30.
 */
@Module
public class DashboardModule {

    private final DashboardContract.View mView;

    public DashboardModule(DashboardContract.View view) {
        mView = view;
    }

    @Provides
    DashboardContract.View providesView() {
        return mView;
    }
}
