package com.agadu.adultmore.dashboard;

/**
 * Created by Yoga on 2017-03-05.
 */

public interface DashboardContract {
    interface View {
        void showError(String errorText);

        void onBiteCheckButtonClick();

        void onTimeCheckButtonClick();

        void onStretchingButtonClick();

        void onCultureButtonClick();
    }
}
