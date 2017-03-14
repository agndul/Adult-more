package com.agadu.adultmore.timecheck;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Yoga on 2016-09-11.
 */
public class TimeCheckPresenter implements TimeCheckContract.Presenter{


    protected String timeFormat = "HH:mm:ss";
    protected String dateFormat = "yyyy-MM-dd";

    protected SimpleDateFormat formatterTime = new SimpleDateFormat(timeFormat);
    protected SimpleDateFormat formatterDate = new SimpleDateFormat(dateFormat);

    private TimeCheckContract.OuterView mView;

    @Inject
    TimeCheckPresenter(TimeCheckContract.OuterView view) {
        mView = view;
    }


    public void getTimeCheck(Realm mTimeCheckRealm){

        mTimeCheckRealm.beginTransaction();

        TimeCheckObject timeCheckObject = mTimeCheckRealm.createObject(TimeCheckObject.class);

        timeCheckObject.setStartDate(formatterDate.format(Calendar.getInstance().getTime()));
        timeCheckObject.setStartTime(formatterTime.format(Calendar.getInstance().getTime()));
        timeCheckObject.setUserId(0);

        mTimeCheckRealm.commitTransaction();

    }
}
