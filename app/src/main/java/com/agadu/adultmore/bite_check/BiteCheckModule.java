package com.agadu.adultmore.bite_check;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yoga on 2017-03-05.
 */
@Module
public class BiteCheckModule {

    private final BiteCheckContract.View mView;

    public BiteCheckModule(BiteCheckContract.View view) {
        mView = view;
    }

    @Provides
    BiteCheckContract.View provideView() {
        return mView;
    }

}
