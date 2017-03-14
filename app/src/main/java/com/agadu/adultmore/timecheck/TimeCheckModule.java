package com.agadu.adultmore.timecheck;

/**
 * Created by Yoga on 2017-03-05.
 */

import dagger.Module;
import dagger.Provides;


@Module
public class TimeCheckModule {

    private final TimeCheckContract.OuterView mView;

    public TimeCheckModule(TimeCheckContract.OuterView view) {
        mView = view;
    }

    @Provides
    TimeCheckContract.OuterView provideTimeCheckView() {
        return mView;
    }

}
