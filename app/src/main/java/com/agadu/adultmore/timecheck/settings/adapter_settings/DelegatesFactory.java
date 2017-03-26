package com.agadu.adultmore.timecheck.settings.adapter_settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoga on 2017-03-26.
 */

class DelegatesFactory {

    public List<SettingsItemDelegate> createDelegates(){
        List<SettingsItemDelegate> list = new ArrayList<>();
        list.add(new ChargeDelegate());
        list.add(new TimeDelegate());
        list.add(new LocationDelegate());

        return list;
    }
}
