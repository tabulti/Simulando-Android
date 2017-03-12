package com.simulando.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;

import com.simulando.R;

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
