package com.agadu.adultmore.timecheck;

/**
 * Created by Yoga on 2016-09-11.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.agadu.adultmore.R;
import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;
import com.agadu.adultmore.timecheck.settings.TimecheckSettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class TimeCheckActivity extends AppCompatActivity implements TimeCheckContract.OuterView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.pager) ViewPager mPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Inject TimeCheckPresenter mTimeCheckPresenter;
    @Inject Realm mTimeCheckRealm;
    @Inject LocationManager mTimeCheckLocationManager;

    public static String sLocationProvider = LocationManager.NETWORK_PROVIDER;

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
        mTimecheckStatisticsFragment = new TimecheckStatisticsFragment();

        DaggerTimeCheckComponent.builder()
                .databaseModule(new DatabaseModule())
                .locationModule(new LocationModule(this))
                .timeCheckModule(new TimeCheckModule(this, this.mTimecheckActiveFragment, this.mTimecheckStatisticsFragment)).build()
                .inject(this);

        mTimecheckActiveFragment.inject(mTimeCheckPresenter, mTimeCheckRealm, mTimeCheckLocationManager);
        mTimecheckStatisticsFragment.inject(mTimeCheckPresenter, mTimeCheckRealm);
        setupPager();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mTimeCheckLocationManager.requestLocationUpdates(sLocationProvider, 0, 0, locationListener);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_timecheck, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_icon:
                startActivity(new Intent(TimeCheckActivity.this, TimecheckSettingsActivity.class));
                break;
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                break;
        }
        return false;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //todo: show error if disabled, hide error if enabled

        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
            //todo: show error (dialog with expl)
        }
    };

}
