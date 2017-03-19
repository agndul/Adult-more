package com.agadu.adultmore.timecheck;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import butterknife.OnTextChanged;
import io.realm.Realm;

import static butterknife.OnTextChanged.Callback.TEXT_CHANGED;

/**
 * Created by Yoga on 2017-03-09.
 */

public class TimecheckActiveFragment extends Fragment implements TimeCheckContract.InnerView {

    @BindView(R.id.start_btn)
    TextView mTimeButton;
    @BindView(R.id.start_iv)
    ImageView mStartIV;
    @BindView(R.id.start_prl)
    PercentRelativeLayout mStartPRL;
    @BindView(R.id.remote_prl)
    PercentRelativeLayout mRemotePRL;
    @BindView(R.id.excuse_prl)
    PercentRelativeLayout mExcusePRL;
    @BindView(R.id.remote_tv)
    TextView mRemoteTV;
    @BindView(R.id.time_label)
    TextView mHourTextTV;
    @BindView(R.id.reset)
    TextView mResetTV;
    @BindView(R.id.excuse_tv)
    TextView excuseTv;
    @BindView(R.id.excuse_tiet)
    TextInputEditText excuseTiet;
    @BindView(R.id.excuse_til)
    TextInputLayout excuseTil;
    @BindView(R.id.confirm_reset_iv)
    ImageView confirmResetIv;

    private Realm mTimeCheckRealm;
    private TimeCheckContract.Presenter mTimeCheckPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_timecheck_active, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void inject(TimeCheckContract.Presenter mPresenterm, Realm reamldb) {
        mTimeCheckPresenter = mPresenterm;
        mTimeCheckRealm = reamldb;

    }

    @OnClick(R.id.start_prl)
    public void onTimeButtonClick() {
        String excuse = "";
        mStartPRL.setSelected(true);
        if(!excuseTiet.isEnabled()) excuse =  excuseTiet.getText().toString();
        mTimeCheckPresenter.putTimeIntoDB(mTimeCheckRealm, excuse, mRemotePRL.isSelected());
        mHourTextTV.setVisibility(View.VISIBLE);
        mHourTextTV.setText(String.format(getString(R.string.time_at_work), mTimeCheckPresenter.getStartTime()));
        mResetTV.setVisibility(View.VISIBLE);
        blockBiew(true);
    }

    @OnClick(R.id.remote_prl)
    public void onGeolocationClick() {

        mRemotePRL.setSelected(!mRemotePRL.isSelected());
        mRemoteTV.setText(mRemotePRL.isSelected() ? getResources().getString(R.string.label_remote_on) : getResources().getString(R.string.label_remote_off));

    }

    @OnClick(R.id.excuse_prl)
    public void onExcuseClicked() {
        confirmResetIv.setVisibility(View.VISIBLE);
        excuseTil.setVisibility(View.VISIBLE);
        excuseTiet.clearComposingText();

    }

    @OnTextChanged(R.id.excuse_tiet)
    public void onExcuseTextChanged(){
        boolean textNotNull = excuseTiet.getText().toString().length()!=0;
        confirmResetIv.setSelected(textNotNull);

    }
    @OnClick(R.id.confirm_reset_iv)
    public void onConfirmResetClicked() {
        if(confirmResetIv.isSelected()){
            excuseTil.setEnabled(false);
            excuseTiet.setEnabled(false);
            confirmResetIv.setSelected(false);
            excuseTv.setText(getString(R.string.label_excuse_added));
        }else {
            excuseTil.setEnabled(true);
            excuseTiet.setEnabled(true);
            excuseTil.setVisibility(View.GONE);
            confirmResetIv.setVisibility(View.GONE);
            excuseTiet.setText("");
            excuseTv.setText(getString(R.string.label_excuse));
        }
    }
    @OnClick(R.id.reset)
    public void onResetBtnClicked() {

        mStartPRL.setSelected(false);
        mRemotePRL.setSelected(false);
        mHourTextTV.setVisibility(View.GONE);
        mResetTV.setVisibility(View.GONE);
        blockBiew(false);
    }

    public void blockBiew(boolean block) {
        mRemotePRL.setEnabled(!block);
        mStartPRL.setEnabled(!block);
        mExcusePRL.setEnabled(!block);
        confirmResetIv.setEnabled(!block);
    }

}
