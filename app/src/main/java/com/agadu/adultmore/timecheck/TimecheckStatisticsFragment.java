package com.agadu.adultmore.timecheck;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agadu.adultmore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckStatisticsFragment extends Fragment implements TimeCheckContract.SecondInnerView, TimeCheckStatisticsAdapter.TimeCheckAdapterActions {

    @BindView(R.id.timecheck_stats_rv)
    RecyclerView timecheckStatsRv;

    private Realm mTimeCheckRealm;
    private TimeCheckContract.Presenter mTimeCheckPresenter;
    private TimeCheckStatisticsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_timecheck_statistics, container, false);
        ButterKnife.bind(this, rootView);
        mTimeCheckPresenter.getHistoryData(mTimeCheckRealm);
        return rootView;
    }
    @Override
    public void inject(TimeCheckContract.Presenter timeCheckPresenter, Realm timeCheckRealm) {
        this.mTimeCheckPresenter = timeCheckPresenter;
        this.mTimeCheckRealm = timeCheckRealm;
    }
    @Override
    public void initAdapter(List<TimeCheckObj> timecheckObj){
        adapter = new TimeCheckStatisticsAdapter(this, timecheckObj);
        timecheckStatsRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        timecheckStatsRv.setAdapter(adapter);
    }

    @Override
    public void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onExcuseClicked(final int layoutPosition) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(TimecheckStatisticsFragment.this.getContext(), R.style.DialogStyle);
        builder.setTitle(R.string.label_your_excuse)
                .setMessage(mTimeCheckPresenter.getStats(layoutPosition).getExcuse())
                .setPositiveButton(R.string.excuse_accepted, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTimeCheckPresenter.setExcuseAccepted(mTimeCheckRealm, layoutPosition, true);
                    }
                })
                .setNegativeButton(R.string.excuse_rejected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTimeCheckPresenter.setExcuseAccepted(mTimeCheckRealm, layoutPosition, false);
                    }
                })

                .show();
    }
}
