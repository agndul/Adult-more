package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;
import com.agadu.adultmore.helpers.CheckTextFieldsHelper;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by Yoga on 2017-03-26.
 */

class ChargeDelegate implements SettingsItemDelegate {

    public static final int CHARGE_TYPE = 1;

    private ChargeViewHolder viewHolder;
    private static ChargeData chargeData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.partial_charge_delegate, parent, false);
        chargeData = new ChargeData();
        viewHolder = new ChargeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SettingsData data) {
        viewHolder.setData(data);
    }

    @Override
    public int getViewType() {
        return CHARGE_TYPE;
    }

    public ChargeData getData() {
        return chargeData;
    }

    @Override
    public boolean isAnyFieldEmpty() {
        return CheckTextFieldsHelper.checkFieldsEmpty(viewHolder.initTiet, viewHolder.stepTiet, viewHolder.maxChargeTiet);
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
        private SettingsData data;

        @OnTextChanged(R.id.init_tiet)
        public void onInitTextChanged(){

            chargeData.setInitialCharge(!initTiet.getText().toString().isEmpty()? Float.valueOf(initTiet.getText().toString()) : 0f);
        }

        @OnTextChanged(R.id.step_tiet)
        public void onDiffTextChanged(){
            chargeData.setDifference(!stepTiet.getText().toString().isEmpty()? Float.valueOf(stepTiet.getText().toString()) : 0f);

        }
        @OnTextChanged(R.id.max_charge_tiet)
        public void onMaxTextChanged(){
            chargeData.setMaxCharge(!maxChargeTiet.getText().toString().isEmpty()? Float.valueOf(maxChargeTiet.getText().toString()) : 0f);
        }

        ChargeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(SettingsData data) {

            initTiet.setText(String.valueOf(data.getInitialCharge()));
            stepTiet.setText(String.valueOf(data.getDifference()));
            maxChargeTiet.setText(String.valueOf(data.getMaxCharge()));

        }

    }
}
