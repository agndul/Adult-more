package com.agadu.adultmore.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.agadu.adultmore.R;
import com.agadu.adultmore.bite_check.BiteCheckActivity;
import com.agadu.adultmore.general.AdultMoreApp;
import com.agadu.adultmore.timecheck.TimeCheckActivity;
import com.agadu.adultmore.timecheck.settings.SettingsData;
import com.agadu.adultmore.timecheck.settings.TimecheckSettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject Context mContext;
    @Inject DashboardPresenter mDashboardPresenter;
    @Inject Realm mTimeCheckRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerDashboardComponent.builder()
                .applicationComponent(((AdultMoreApp)getApplication()).getApplicationComponent())
                .dashboardModule(new DashboardModule(this))
                .build()
                .inject(this);

        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_dashboard, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_icon:
                Toast.makeText(this, R.string.error_to_do_section, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    @OnClick(R.id.time_check_btn)
    public void onTimeCheckButtonClick() {
        Intent intent;
        if(!mTimeCheckRealm.where(SettingsData.class).findAll().isEmpty()) {
            intent = new Intent(this, TimeCheckActivity.class);
        }else {
            intent = new Intent(this, TimecheckSettingsActivity.class);
        }
        startActivity(intent);
    }

    @OnClick(R.id.eating_btn)
    public void onBiteCheckButtonClick() {
        Intent intent = new Intent(this, BiteCheckActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.stretch_out_btn)
    public  void onStretchingButtonClick() {
        Toast.makeText(this, R.string.error_to_do_section, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.culture_btn)
    public void onCultureButtonClick() {
        Toast.makeText(this, R.string.error_to_do_section, Toast.LENGTH_SHORT).show();
    }

    public void showError(String errorText) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show();
    }
}
