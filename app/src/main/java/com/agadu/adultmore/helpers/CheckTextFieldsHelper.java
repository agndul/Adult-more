package com.agadu.adultmore.helpers;

import android.support.design.widget.TextInputEditText;

/**
 * Created by Yoga on 2017-04-13.
 */

public class CheckTextFieldsHelper {
    public static boolean checkFieldsEmpty(TextInputEditText ... textInputEditTexts){
        boolean isEmpty = false;
        for (TextInputEditText textInputEditText: textInputEditTexts) {
            if (textInputEditText.getText().length() == 0) isEmpty = true;
        }
        return isEmpty;
    }
}
