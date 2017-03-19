package com.agadu.adultmore.timecheck;

/**
 * Created by Yoga on 2016-09-11.
 */

import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.agadu.adultmore.R;
import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class TimeCheckActivity extends AppCompatActivity implements TimeCheckContract.OuterView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Inject
    TimeCheckPresenter mTimeCheckPresenter;
    @Inject
    Realm mTimeCheckRealm;
    @Inject
    LocationManager mTimeCheckLocationManager;

    private TimeCheckViewPagerAdapter mPagerAdapter;
    private TimecheckActiveFragment mTimecheckActiveFragment;
    private TimecheckStatisticsFragment mTimecheckStatisticsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecheck);
        ButterKnife.bind(this);
        // Create the presenter
        mTimecheckActiveFragment = new TimecheckActiveFragment();
        DaggerTimeCheckComponent.builder()
                .databaseModule(new DatabaseModule())
                .locationModule(new LocationModule(this))
                .timeCheckModule(new TimeCheckModule(this, this.mTimecheckActiveFragment)).build()
                .inject(this);
        mTimecheckActiveFragment.inject(mTimeCheckPresenter, mTimeCheckRealm, mTimeCheckLocationManager);
        mTimecheckStatisticsFragment = new TimecheckStatisticsFragment();
        mTimecheckStatisticsFragment.inject(mTimeCheckPresenter, mTimeCheckRealm, mTimeCheckLocationManager);
        mTimeCheckPresenter.setLocationListener(mTimeCheckLocationManager);
        setupPager();
    }

    public void setupPager() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.timecheck_title);
        mPagerAdapter = new TimeCheckViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(mTimecheckActiveFragment, getString(R.string.timecheck_page_active_title));
        mPagerAdapter.addFragment(mTimecheckStatisticsFragment, getString(R.string.timecheck_page_stats_title));
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);

    }

}
