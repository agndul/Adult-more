package com.agadu.adultmore.dashboard;

import javax.inject.Inject;

/**
 * Created by Yoga on 2016-10-30.
 */

public class DashboardPresenter {

    DashboardContract.View mView;

    @Inject
    DashboardPresenter(DashboardContract.View view) {
        mView = view;
    }


}
