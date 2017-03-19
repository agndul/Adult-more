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
    private final TimeCheckContract.SecondInnerView mSecondInnerView;

    public TimeCheckModule(TimeCheckContract.OuterView view, TimeCheckContract.InnerView innerView, TimeCheckContract.SecondInnerView secondInnerView) {
        mView = view;
        mInnerView = innerView;
        mSecondInnerView = secondInnerView;
    }

    @Provides
    TimeCheckContract.OuterView provideTimeCheckView() {
        return mView;
    }

    @Provides
    TimeCheckContract.InnerView provideTimeCheckInnerView() {
        return mInnerView;
    }

    @Provides
    TimeCheckContract.SecondInnerView provideTimeCheckSecondInnerView() {
        return mSecondInnerView;
    }

}
