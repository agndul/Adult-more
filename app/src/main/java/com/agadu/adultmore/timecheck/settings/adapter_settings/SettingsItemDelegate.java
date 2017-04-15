package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agadu.adultmore.timecheck.settings.SettingsData;

/**
 * Created by Yoga on 2017-03-26.
 */

interface SettingsItemDelegate {

    RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent);
    void onBindViewHolder(RecyclerView.ViewHolder holder, SettingsData data);
    int getViewType();
    boolean isAnyFieldEmpty();
}

