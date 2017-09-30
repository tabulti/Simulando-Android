package com.simulando.joaopaulodribeiro.simulando.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.simulando.joaopaulodribeiro.simulando.R;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class Utils {

    public static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static SharedPreferences getPreferencesInstance(Context context){
        return context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public static void saveUserToken (Context context, String userToken) {
        SharedPreferences.Editor editor = getPreferencesInstance(context).edit();
        editor.putString(context.getString(R.string.user_token), userToken);
        editor.commit();
    }

    public static String getUserToken (Context context) {
        return getPreferencesInstance(context).getString(context.getString(R.string.user_token), "");
    }
}
