package com.agadu.adultmore.timecheck;

/**
 * Created by Yoga on 2017-03-05.
 */

import dagger.Module;
import dagger.Provides;


@Module
public class TimeCheckModule {

    private final TimeCheckContract.OuterView mView;
    private final TimeCheckContract.InnerView mInnerView;

    public TimeCheckModule(TimeCheckContract.OuterView view, TimeCheckContract.InnerView innerView) {
        mView = view;
        mInnerView = innerView;
    }

    @Provides
    TimeCheckContract.OuterView provideTimeCheckView() {
        return mView;
    }

    @Provides
    TimeCheckContract.InnerView provideTimeCheckInnerView() {
        return mInnerView;
    }

}
