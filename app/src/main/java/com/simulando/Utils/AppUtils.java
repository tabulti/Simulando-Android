package com.simulando.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.simulando.R;
import com.simulando.UI.Dashboard.DashboardActivity;
import com.simulando.UI.Intro.IntroActivity;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class AppUtils {

    private static AlertDialog mDialog;
    private static AlertDialog.Builder mBuilder;

    public static void showLoadingDialog(Context context) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        mBuilder = new AlertDialog.Builder(context);
        mBuilder.setView(R.layout.dialog_loading);
        mDialog = mBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    public static void showMessageDialog(Context context, int message,
                                         int positiveText, DialogInterface.OnClickListener positiveAction,
                                         int negativeText, DialogInterface.OnClickListener negativeAction) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        mBuilder = new AlertDialog.Builder(context);
        mBuilder.setMessage(message);
        if (positiveText != -1 && positiveAction != null) {
            mBuilder.setPositiveButton(positiveText, positiveAction);
        }

        if (negativeText != -1 && negativeAction != null) {
            mBuilder.setNegativeButton(positiveText, positiveAction);
        }
        mDialog = mBuilder.create();
        mDialog.show();
    }


    public static void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.hide();
            mDialog = null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static void goToIntro(Context context) {
        Intent intent = new Intent(context, IntroActivity.class);
        context.startActivity(intent);
    }

    public static void goToDashboard(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
