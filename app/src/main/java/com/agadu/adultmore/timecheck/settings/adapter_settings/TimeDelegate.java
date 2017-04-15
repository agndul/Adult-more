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

public class TimeDelegate implements SettingsItemDelegate {
    public static final int TIME_TYPE = 2;

    private TimeViewHolder viewHolder;
    private static TimeData timeData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_time_delegate, parent, false);
        viewHolder = new TimeViewHolder(view);
        timeData = new TimeData();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SettingsData data) {
        viewHolder.setData(data);
    }

    @Override
    public int getViewType() {
        return TIME_TYPE;
    }

    @Override
    public boolean isAnyFieldEmpty() {
        return CheckTextFieldsHelper.checkFieldsEmpty(viewHolder.startTimeTiet, viewHolder.lateMinsTiet, viewHolder.maxTimeTiet);
    }

    public TimeData getData() {
        return timeData;
    }

    static class TimeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.start_time_tiet)
        TextInputEditText startTimeTiet;
        @BindView(R.id.start_time_til)
        TextInputLayout startTimeTil;
        @BindView(R.id.late_mins_tiet)
        TextInputEditText lateMinsTiet;
        @BindView(R.id.late_mins_til)
        TextInputLayout lateMinsTil;
        @BindView(R.id.max_time_tiet)
        TextInputEditText maxTimeTiet;
        @BindView(R.id.max_time_til)
        TextInputLayout maxTimeTil;


        @OnTextChanged(R.id.max_time_tiet)
        public void onMaxTimeChanged(){
            timeData.setMaxLateHours(maxTimeTiet.getText().toString());
        }
        @OnTextChanged(R.id.start_time_tiet)
        public void onStartTimeChanged(){
            timeData.setStartTime(startTimeTiet.getText().toString());
        }
        @OnTextChanged(R.id.late_mins_tiet)
        public void onStepTimeChanged(){
            timeData.setDifferenceTime(lateMinsTiet.getText().toString());
        }

        TimeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        public void setData(SettingsData data) {
            startTimeTiet.setText(data.getStartTime());
            lateMinsTiet.setText(String.valueOf(data.getDiffTime()));
            maxTimeTiet.setText(String.valueOf(data.getMaxTime()));
        }
    }
}