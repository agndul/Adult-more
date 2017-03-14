package com.agadu.adultmore.bite_check;

import javax.inject.Inject;

/**
 * Created by Yoga on 2017-03-05.
 */

public class BiteCheckPresenter {

    private BiteCheckContract.View mView;

    @Inject
    BiteCheckPresenter(BiteCheckContract.View view) {
        mView = view;
    }

}
