package com.agadu.adultmore.timecheck.settings;

import javax.inject.Inject;

/**
 * Created by Yoga on 2017-03-26.
 */

public class TimeCheckSettingsPresenter implements TimecheckSettingsContract.Presenter {

    private TimecheckSettingsContract.View mView;


    @Inject
    TimeCheckSettingsPresenter(TimecheckSettingsContract.View view) {
        mView = view;
    }

}
