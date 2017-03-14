package com.agadu.adultmore.dashboard;

import com.agadu.adultmore.general.components.ApplicationComponent;
import com.agadu.adultmore.general.scope.UserScope;

import dagger.Component;

/**
 * Created by Yoga on 2017-03-05.
 */
@UserScope
@Component(modules = {DashboardModule.class}, dependencies = ApplicationComponent.class)
public interface DashboardComponent {
    void inject(DashboardActivity activity);
    // to update the fields in your activities
}