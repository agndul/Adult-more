package com.agadu.adultmore.timecheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agadu.adultmore.R;
import com.agadu.adultmore.helpers.TimeFormatsHelper;
import com.agadu.adultmore.timecheck.settings.SettingsData;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yoga on 2017-03-19.
 */

public class TimeCheckStatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface TimeCheckAdapterActions {

        void onExcuseClicked(int layoutPosition);

    }
    private TimeCheckAdapterActions mCallback;
    private List<TimeCheckObj> timeCheckObject;
    private Context context;
    private StatsViewHolder viewHolder;

     public TimeCheckStatisticsAdapter(TimeCheckAdapterActions callback, List<TimeCheckObj> timeCheckObject) {
        this.mCallback = callback;
        this.timeCheckObject = timeCheckObject;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_timecheck_statistics, parent, false);
        viewHolder = new StatsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            viewHolder.setData(timeCheckObject.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return timeCheckObject.size();
    }


    public class StatsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.remote_indicator_iv)
        ImageView remoteIndicatorIv;
        @BindView(R.id.date_label_tv)
        TextView dateLabelTv;
        @BindView(R.id.money_added_tv)
        TextView moneyAddedTv;
        @BindView(R.id.late_label_tv)
        TextView lateLabelTv;
        @BindView(R.id.read_excuse_iv)
        ImageView readExcuseIv;

        public StatsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setData(TimeCheckObj timeCheckObject) throws ParseException {

            remoteIndicatorIv.setVisibility(timeCheckObject.isRemote() ? View.VISIBLE : View.GONE);
            dateLabelTv.setText(new TimeFormatsHelper().returnPreviewDate(timeCheckObject.getTime()));
            int diffMins = (int) new TimeFormatsHelper().returnMinsDiff(timeCheckObject.getStartTime());
            int diffHours = diffMins/60;
            float money=0;

            if (diffHours > 0) {
                money = SettingsData.LAST_CHARGE;
                lateLabelTv.setText(R.string.late_label_hour);
            } else if (diffHours == 0 && diffMins > 0){
                money = countCharge(diffMins);
                lateLabelTv.setText(String.format(context.getString(R.string.late_label_mins), diffMins));
            } else {
                money=0;
                lateLabelTv.setText(R.string.late_label_no);
            }
            moneyAddedTv.setText(money==0 ? context.getString(R.string.no_charge_label) : String.format(Locale.UK, context.getString(R.string.charge_label), money));

            readExcuseIv.setVisibility(!timeCheckObject.getExcuse().isEmpty() ? View.VISIBLE : View.GONE);
            if(timeCheckObject.isExcuseAccepted() != null){
                readExcuseIv.setImageResource(timeCheckObject.isExcuseAccepted() ? R.drawable.ic_comment_check_grey600_24dp : R.drawable.ic_comment_alert_grey600_24dp);
            }
        }

        private float countCharge(int minsLate) {
            float charge = SettingsData.INITIAL_CHARGE;
            if(minsLate>15){
                for(int i=15; i<60; i+=15){
                    if(minsLate>i)
                        charge += SettingsData.DIFFERENCE;
                    else
                        break;
                }
            }
            return charge;
        }
        @OnClick(R.id.read_excuse_iv)
        public void onReadExcuseClicked(){
            mCallback.onExcuseClicked(getLayoutPosition());
        }
    }
}
