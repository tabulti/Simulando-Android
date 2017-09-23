package com.simulando.joaopaulodribeiro.simulando.utils;

import android.text.TextUtils;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class Utils {

    public static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
