package com.agadu.adultmore.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.agadu.adultmore.R;
import com.agadu.adultmore.general.AdultMoreApp;
import com.agadu.adultmore.stretch_out.StretchActivity;
import com.agadu.adultmore.timecheck.TimeCheckActivity;
import com.agadu.adultmore.timecheck.settings.SettingsData;
import com.agadu.adultmore.timecheck.settings.TimecheckSettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.cl) ConstraintLayout constraintLayout;

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
                showError(getString(R.string.error_to_do_section));
                break;
            default:
                break;
        }
        return false;
    }

    @OnClick(R.id.time_check_btn)
    public void onTimeCheckButtonClick() {
        if(!mTimeCheckRealm.where(SettingsData.class).findAll().isEmpty())
            startActivity(new Intent(this, TimeCheckActivity.class));
        else
            startActivity(new Intent(this, TimecheckSettingsActivity.class));
    }

    @OnClick(R.id.eating_btn)
    public void onBiteCheckButtonClick() {
        showError(getString(R.string.error_to_do_section));
    }

    @OnClick(R.id.stretch_out_btn)
    public  void onStretchingButtonClick() {

        startActivity(new Intent(this, StretchActivity.class));
    }

    @OnClick(R.id.culture_btn)
    public void onCultureButtonClick() {
        showError(getString(R.string.error_to_do_section));
    }

    public void showError(String errorText) {
        Snackbar.make(constraintLayout, errorText, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
