package com.simulando.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Luciano José on 11/02/2017.
 */

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static String getConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (null != netInfo) {
            if (netInfo.getType() == cm.TYPE_WIFI) {
                return "WIFI";
            }

            if (netInfo.getType() == cm.TYPE_MOBILE)
                return "MOBILE";
        }

        return "NO_CONNECTION";
    }

}
