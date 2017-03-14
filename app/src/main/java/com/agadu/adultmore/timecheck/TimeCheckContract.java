package com.agadu.adultmore.timecheck;

import io.realm.Realm;

/**
 * Created by Yoga on 2016-10-30.
 */

public interface TimeCheckContract {

    interface OuterView {
    }

    interface InnerView {
        void inject(TimeCheckPresenter mPresenterm, Realm reamldb);
    }

    interface Presenter {

        void getTimeCheck(Realm mTimeCheckRealm);
    }
}
