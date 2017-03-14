package com.agadu.adultmore.bite_check;

import com.agadu.adultmore.general.modules.NetModule;
import com.agadu.adultmore.general.scope.UserScope;

import dagger.Component;

/**
 * Created by Yoga on 2017-03-05.
 */
@UserScope
@Component(modules = {NetModule.class, BiteCheckModule.class})
public interface BiteCheckComponent {

    void inject(BiteCheckActivity activity);
}
