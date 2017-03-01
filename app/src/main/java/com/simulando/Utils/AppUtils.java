package com.simulando.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.simulando.UI.Dashboard.DashboardActivity;
import com.simulando.UI.Intro.IntroActivity;

/**
 * Created by Luciano José on 01/02/2017.
 */

public class AppUtils {

    private static Toast mToast;
    private static Snackbar mSnackbar;

    public static void goToIntro(Context context) {
        Intent intent = new Intent(context, IntroActivity.class);
        context.startActivity(intent);
    }

    public static void goToDashboard(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void showToast(Context context, int message, int duration) {
        mToast = Toast.makeText(context, message, duration);
        mToast.show();
    }

    public static void showSnackBar(View view, int message, int actionMesssage, View.OnClickListener action) {
        mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setAction(actionMesssage, action);
        mSnackbar.show();
    }

    public static void showSnackBar(View view, int message) {
        mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();
    }

}

