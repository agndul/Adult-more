package com.agadu.adultmore.timecheck.settings.adapter_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agadu.adultmore.timecheck.settings.SettingsData;

import java.util.List;

/**
 * Created by Yoga on 2017-03-26.
 */

public class TCSettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<SettingsItemDelegate> delegates;
    private Context mContext;

    public TCSettingsAdapter(Context context) {
        mContext = context;
        delegates = new DelegatesFactory().createDelegates();
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
        delegates.get(position).onBindViewHolder(holder, new SettingsData());
    }

    @Override
    public int getItemCount() {
        return delegates.size();
    }

    @Override
    public int getItemViewType(int position){
        return delegates.get(position).getViewType();
    }
}
