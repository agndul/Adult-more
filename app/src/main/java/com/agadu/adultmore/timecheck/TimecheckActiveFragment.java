package com.agadu.adultmore.timecheck;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agadu.adultmore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckActiveFragment extends Fragment implements TimeCheckContract.InnerView{

    @BindView(R.id.start_btn)
    TextView mTimeButton;

    @BindView(R.id.start_iv)
    ImageView mStartIV;

    @BindView(R .id.start_prl)
    PercentRelativeLayout mStartPRL;

    @BindView(R .id.remote_prl)
    PercentRelativeLayout mRemotePRL;
    @BindView(R .id.remote_tv)
    TextView mRemoteTV;

    private Realm mTimeCheckRealm;
    private TimeCheckPresenter mTimeCheckPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_timecheck_active, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void inject(TimeCheckPresenter mPresenterm, Realm reamldb) {
        mTimeCheckPresenter = mPresenterm;
        mTimeCheckRealm = reamldb;

    }

    @OnClick(R.id.start_prl)
    public void onTimeButtonClick() {

        mStartIV.setColorFilter(R.color.icons);
        mStartPRL.setSelected(true);
        mTimeButton.setTextColor(getResources().getColor(R.color.accent));
        mTimeCheckPresenter.getTimeCheck(mTimeCheckRealm);

    }

    @OnClick(R.id.remote_prl)
    public void onGeolocationClick() {

        mRemotePRL.setSelected(!mRemotePRL.isSelected());
        mRemoteTV.setTextColor(mRemotePRL.isSelected() ? getResources().getColor(R.color.accent) : getResources().getColor(R.color.primary_dark));
        mRemoteTV.setText(mRemotePRL.isSelected() ? getResources().getString(R.string.label_remote_on) : getResources().getString(R.string.label_remote_off));

    }



}
