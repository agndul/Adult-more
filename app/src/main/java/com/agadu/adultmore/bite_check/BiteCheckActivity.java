package com.agadu.adultmore.bite_check;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.agadu.adultmore.R;
import com.agadu.adultmore.general.modules.NetModule;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.agadu.adultmore.general.Constants.API_ENDPOINT_BITE_MODULE;

/**
 * Created by Yoga on 2017-03-05.
 */
public class BiteCheckActivity extends AppCompatActivity implements BiteCheckContract.View{
    @Inject
    BiteCheckPresenter mBiteCheckPresenter;
    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecheck_settings);

        DaggerBiteCheckComponent
                .builder()
                .netModule(new NetModule(API_ENDPOINT_BITE_MODULE))
                .biteCheckModule(new BiteCheckModule(this))
                .build()
                .inject(this);

    }

}
