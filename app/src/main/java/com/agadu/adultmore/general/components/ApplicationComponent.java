package com.agadu.adultmore.general.components;

import android.app.Application;
import android.content.Context;

import com.agadu.adultmore.general.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yoga on 2017-03-04.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
    Application application();

}