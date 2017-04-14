package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agadu.adultmore.timecheck.settings.SettingsData;

import java.util.List;

import static com.agadu.adultmore.timecheck.settings.adapter_settings.ChargeDelegate.CHARGE_TYPE;
import static com.agadu.adultmore.timecheck.settings.adapter_settings.LocationDelegate.LOCATION_TYPE;
import static com.agadu.adultmore.timecheck.settings.adapter_settings.TimeDelegate.TIME_TYPE;

/**
 * Created by Yoga on 2017-03-26.
 */

public class TCSettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<SettingsItemDelegate> delegates;
    private Context mContext;
    private AdapterGeneralData mData;
    private SettingsData mSettingsData;


    public TCSettingsAdapter(Context context, SettingsData settingsData) {
        mContext = context;
        mData = new AdapterGeneralData();
        delegates = new DelegatesFactory().createDelegates();
        mSettingsData = settingsData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        for (SettingsItemDelegate delegate: delegates)
            if(delegate.getViewType()==viewType)
                viewHolder = delegate.onCreateViewHolder(mContext, parent);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegates.get(position).onBindViewHolder(holder, mSettingsData);
    }

    @Override
    public int getItemCount() {
        return delegates.size();
    }

    @Override
    public int getItemViewType(int position){
        return delegates.get(position).getViewType();
    }

    public boolean isAnyFieldEmpty(){
        boolean isAnyFieldEmpty=false;
        int pos = 0;
        for (SettingsItemDelegate delegate: delegates) {
            if (delegate.getViewType() == getItemViewType(pos)) {
                isAnyFieldEmpty = delegate.isAnyFieldEmpty();
                if (isAnyFieldEmpty) break;
            }
            pos++;
        }
        return isAnyFieldEmpty;
    }

    public AdapterGeneralData getData() {
        for (SettingsItemDelegate delegate: delegates) {
            switch (delegate.getViewType()) {
                case CHARGE_TYPE:
                    mData.setChargeData((ChargeData) delegate.getData());
                     break;
                case TIME_TYPE:
                    mData.setTimeData((TimeData) delegate.getData());
                    break;
                case LOCATION_TYPE:
                    mData.setLocationData((LocationData) delegate.getData());
                    break;
            }
        }
        return mData;
    }
}
