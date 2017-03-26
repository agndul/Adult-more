package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yoga on 2017-03-26.
 */

public class LocationDelegate implements SettingsItemDelegate {

    private static final int LOCATION_TYPE = 3;


    private LocationViewHolder viewHolder;

    @Override
    public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_delegate, parent, false);
        viewHolder = new LocationViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, SettingsData data) {
    }

    @Override
    public int getViewType() {
        return LOCATION_TYPE;
    }

    static class LocationViewHolder extends ViewHolder {

        @BindView(R.id.location_tiet)
        TextInputEditText locationTiet;
        @BindView(R.id.location_til)
        TextInputLayout locationTil;
        @BindView(R.id.mapview)
        MapView mapview;

        public LocationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
