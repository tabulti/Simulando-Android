package com.simulando.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Luciano José on 27/02/2017.
 */

public class DateUtils {

    private static String fullText;
    private static String hours;
    private static String minutes;
    private static String date;

    private static Calendar mCalendar;
    private static Locale brLocale = new Locale("pt", "BR");
    private static SimpleDateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy", brLocale);
    private static Date currentDate;

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


}
