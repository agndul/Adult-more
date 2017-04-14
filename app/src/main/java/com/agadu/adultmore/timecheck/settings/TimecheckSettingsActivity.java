package com.agadu.adultmore.timecheck.settings;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.agadu.adultmore.R;
import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;
import com.agadu.adultmore.timecheck.settings.adapter_settings.TCSettingsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckSettingsActivity extends AppCompatActivity
        implements TimecheckSettingsContract.View {

    @Inject TimeCheckSettingsPresenter mTimeCheckSettingsPresenter;
    @Inject Realm mTimeCheckRealm;
    @Inject LocationManager mTimeCheckLocationManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.settings_rv) RecyclerView settingsRv;

    private TCSettingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecheck_settings);
        ButterKnife.bind(this);

        DaggerTimecheckSettingsComponent
                .builder()
                .databaseModule(new DatabaseModule())
                .locationModule(new LocationModule(this))
                .timeCheckSettingsModule(new TimeCheckSettingsModule(this)).build()
                .inject(this);

        setupToolbar();
        mTimeCheckSettingsPresenter.initView(mTimeCheckRealm);

    }
    @Override
    public void initAdapter(SettingsData settingsData) {
        adapter = new TCSettingsAdapter(this, settingsData);
        settingsRv.setLayoutManager(new LinearLayoutManager(this));
        settingsRv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_icon:
                saveSettings();
                finish();
                break;
            case R.id.cancel_icon:
                super.onBackPressed();
                return true;
            default:
                break;
        }
        return false;
    }

    private void saveSettings() {
        if(adapter.isAnyFieldEmpty()){
            Toast.makeText(this, "All of the fields should be filled", Toast.LENGTH_LONG).show();
        }else {
            mTimeCheckSettingsPresenter.removeLast(mTimeCheckRealm);
            mTimeCheckSettingsPresenter.updateSettings(mTimeCheckRealm, adapter.getData());
        }
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.settings_title);
    }
}
