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

public class CommonUtils {

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




    public static void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.hide();
            mDialog = null;
        }
    }





}
