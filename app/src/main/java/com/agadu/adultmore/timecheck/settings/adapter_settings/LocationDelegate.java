package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;
import com.agadu.adultmore.helpers.CheckTextFieldsHelper;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by Yoga on 2017-03-26.
 */

class LocationDelegate implements SettingsItemDelegate {

    public static final int LOCATION_TYPE = 3;

    private LocationViewHolder viewHolder;
    private static LocationData mLocationData;

    @Override
    public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_location_delegate, parent, false);
        viewHolder = new LocationViewHolder(view);
        mLocationData = new LocationData();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, SettingsData data) {
        viewHolder.setData(data);
    }

    @Override
    public int getViewType() {
        return LOCATION_TYPE;
    }

    @Override
    public boolean isAnyFieldEmpty() {
        return CheckTextFieldsHelper.checkFieldsEmpty(viewHolder.locationTiet);
    }

    @Override
    public AdapterData getData() {
        return mLocationData;
    }

    static class LocationViewHolder extends ViewHolder {

        @BindView(R.id.location_tiet)
        TextInputEditText locationTiet;
        @BindView(R.id.location_til)
        TextInputLayout locationTil;

        @OnTextChanged(R.id.location_tiet)
        public void onLocationChanged(){
            mLocationData.setLocation(locationTiet.getText().toString());
        }

        LocationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SettingsData data) {

            locationTiet.setText(String.valueOf(data.getLocation()));

        }
    }
}
