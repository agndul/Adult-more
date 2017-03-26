package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yoga on 2017-03-26.
 */

public class ChargeDelegate implements SettingsItemDelegate {

    private static final int CHARGE_TYPE = 1;

    private ChargeViewHolder viewHolder;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.item_charge_delegate, parent, false);
        viewHolder = new ChargeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SettingsData data) {

    }

    @Override
    public int getViewType() {
        return CHARGE_TYPE;
    }

    static class ChargeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.init_tiet)
        TextInputEditText initTiet;
        @BindView(R.id.init_til)
        TextInputLayout initTil;
        @BindView(R.id.step_tiet)
        TextInputEditText stepTiet;
        @BindView(R.id.step_til)
        TextInputLayout stepTil;
        @BindView(R.id.max_charge_tiet)
        TextInputEditText maxChargeTiet;
        @BindView(R.id.max_charge_til)
        TextInputLayout maxChargeTil;

        ChargeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
