package com.agadu.adultmore.timecheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class TimecheckStatisticsFragment extends Fragment implements TimeCheckContract.SecondInnerView {

    @BindView(R.id.timecheck_stats_rv)
    RecyclerView timecheckStatsRv;

    private Realm mTimeCheckRealm;
    private TimeCheckContract.Presenter mTimeCheckPresenter;
    private TimeCheckStatisticsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_timecheck_settings, container, false);
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
    public void initAdapter(List<TimeCheckObject> timecheckObj){
        adapter = new TimeCheckStatisticsAdapter(timecheckObj);
        timecheckStatsRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        timecheckStatsRv.setAdapter(adapter);
    }
}
