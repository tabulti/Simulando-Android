package com.simulando.joaopaulodribeiro.simulando.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.simulando.joaopaulodribeiro.simulando.R;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class Utils {

    public static final String CALENDAR_TIME_SAVED = "calendar_time_saved_key";
    public static final String COUNTDOWN_TIME_SAVED = "countdown_time_saved_key";

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
        editor.apply();
    }

    public static void saveTimeInMilliseconds(Context context, long time, String key) {
        SharedPreferences.Editor editor = getPreferencesInstance(context).edit();
        editor.putLong(key, time);
        editor.apply();
    }

    public static long getSavedTimeInMilliseconds(Context context, String key) {
        return getPreferencesInstance(context).getLong(key, 0L);
    }

    public static String getUserToken(Context context) {
        return getPreferencesInstance(context).getString(context.getString(R.string.user_token), "");
    }

    public static void saveAnswer(Context context, String jsonValue, String key) {
        SharedPreferences.Editor editor = getPreferencesInstance(context).edit();
        editor.putString(key, jsonValue);
        editor.apply();
    }

    /**
     *
     * @param context
     * @param key
     * @return is a json String
     */
    public static String getListOfAnswers (Context context, String key) {
        return getPreferencesInstance(context).getString(key, "");
    }
}
