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

import static com.agadu.adultmore.helpers.TimeFormatsHelper.MINS_IN_HOUR;

/**
 * Created by Yoga on 2017-03-19.
 */

public class TimeCheckStatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface TimeCheckAdapterActions {

        void onExcuseClicked(int layoutPosition);

    }
    private TimeCheckAdapterActions mCallback;
    private List<TimeCheckObj> timeCheckObject;
    private SettingsData settingsData;
    private Context context;
    private StatsViewHolder viewHolder;

     public TimeCheckStatisticsAdapter(List<TimeCheckObj> timeCheckObject, SettingsData settingsData, TimeCheckAdapterActions callback) {
        this.mCallback = callback;
        this.timeCheckObject = timeCheckObject;
         this.settingsData = settingsData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timecheck_statistics, parent, false);
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
            dateLabelTv.setText(TimeFormatsHelper.returnPreviewDate(timeCheckObject.getTime()));
            int diffMins = (int) TimeFormatsHelper.returnMinsDiff(timeCheckObject.getStartTime(), settingsData.getStartTime());
            float diffHours = (float) diffMins/60;
            float money;
//todo: fix!! conditions
            if (diffHours > settingsData.getMaxTime()) {
                money = settingsData.getMaxCharge();
                lateLabelTv.setText(String.format(Locale.US, context.getString(R.string.late_label_hour), settingsData.getMaxTime()));
            } else if (diffHours <= settingsData.getMaxTime() && diffMins > 0){
                money = countCharge(diffMins);
                lateLabelTv.setText(String.format(Locale.US, context.getString(R.string.late_label_mins), diffMins));
            } else {
                money = 0;
                lateLabelTv.setText(R.string.late_label_no);
            }
            moneyAddedTv.setText(money==0 ? context.getString(R.string.no_charge_label) : String.format(Locale.UK, context.getString(R.string.charge_label), money));

            readExcuseIv.setVisibility(!timeCheckObject.getExcuse().isEmpty() ? View.VISIBLE : View.GONE);
            if(timeCheckObject.isExcuseAccepted() != null)
                readExcuseIv.setImageResource(timeCheckObject.isExcuseAccepted() ? R.drawable.ic_comment_check_grey600_24dp : R.drawable.ic_comment_alert_grey600_24dp);
        }

        private float countCharge(int minsLate) throws ParseException {
            float charge = settingsData.getInitialCharge();
            float firstMinsLate = settingsData.getDiffTime();
            float maxHoursLate = settingsData.getMaxTime();

            if(minsLate > firstMinsLate){
                for(float i=firstMinsLate; i<maxHoursLate*MINS_IN_HOUR; i+=firstMinsLate){
                    if(minsLate>i)
                        charge += settingsData.getDifference();
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
