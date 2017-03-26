package com.agadu.adultmore.timecheck.settings;

import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.agadu.adultmore.BuildConfig;
import com.agadu.adultmore.R;
import com.agadu.adultmore.general.modules.DatabaseModule;
import com.agadu.adultmore.general.modules.LocationModule;
import com.agadu.adultmore.timecheck.settings.adapter_settings.TCSettingsAdapter;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckSettingsActivity extends AppCompatActivity implements TimecheckSettingsContract.View{

    @Inject
    TimeCheckSettingsPresenter mTimeCheckSettingsPresenter;
    @Inject
    Realm mTimeCheckRealm;
    @Inject
    LocationManager mTimeCheckLocationManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.settings_rv)
    RecyclerView settingsRv;

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
        initAdapter();

    }

    private void initAdapter() {
        adapter = new TCSettingsAdapter(this);
        settingsRv.setLayoutManager(new LinearLayoutManager(this));
        settingsRv.setAdapter(adapter);
    }


    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.settings_title);
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
                //todo: save settings!
                break;
            case R.id.cancel_icon:
                super.onBackPressed();
                return true;
            default:
                break;
        }
        return false;
    }

}
