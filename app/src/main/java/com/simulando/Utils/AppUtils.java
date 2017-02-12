package com.simulando.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.simulando.UI.Dashboard.DashboardActivity;
import com.simulando.UI.Intro.IntroActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Luciano José on 01/02/2017.
 */

public class AppUtils {

    private static String fullText;
    private static String hours;
    private static String minutes;
    private static String date;

    private static Calendar mCalendar;
    private static Locale brLocale = new Locale("pt", "BR");
    private static SimpleDateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy", brLocale);
    private static Date currentDate;
    private static Toast mToast;

    public static String getExamDate(Date startDate, Date endDate) {
        fullText = "";
        mDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        mCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        mCalendar.setTime(startDate);

        hours = toDoubleDigits(mCalendar.get(Calendar.HOUR_OF_DAY));
        minutes = toDoubleDigits(mCalendar.get(Calendar.MINUTE));

        date = hours + ":" + minutes + " do dia " + mDateFormat.format(mCalendar.getTime());
        fullText += date + " até às ";

        mCalendar.setTime(endDate);
        hours = toDoubleDigits(mCalendar.get(Calendar.HOUR_OF_DAY));
        minutes = toDoubleDigits(mCalendar.get(Calendar.MINUTE));

        date = hours + ":" + minutes + " do dia " + mDateFormat.format(mCalendar.getTime());
        fullText += date;

        return fullText;
    }

    public static boolean isExamAvailable(Date startDate, Date endDate) {
        currentDate = new Date();

        if (currentDate.before(startDate) || currentDate.after(endDate)) {
            return false;
        } else if (currentDate.after(startDate) && currentDate.before(endDate)) {
            return true;
        }

        return false;
    }

    public static String toDoubleDigits(int number) {
        if (number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
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

    public static void showToast(Context context, int message, int duration) {
        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToast.show();
    }


}
