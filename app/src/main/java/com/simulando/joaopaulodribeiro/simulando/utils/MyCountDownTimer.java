package com.simulando.joaopaulodribeiro.simulando.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.simulando.joaopaulodribeiro.simulando.Callbacks;

import java.util.Calendar;

/**
 * Created by joao.paulo.d.ribeiro on 03/10/2017.
 */

public class MyCountDownTimer extends CountDownTimer {
    private Context mContext;
    private TextView mTextView;
    private long timeInFuture;
    private Callbacks.OnMillisUntilFinishedListener mListener;


    public MyCountDownTimer(Context context, TextView tv, long millisInFuture, long countDownInterval,
                            Callbacks.OnMillisUntilFinishedListener listener) {
        super(millisInFuture, countDownInterval);
        this.mTextView = tv;
        this.mContext = context;
        this.timeInFuture = millisInFuture;
        this.mListener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeInFuture = millisUntilFinished;
        mTextView.setText(getCorrectTimer(true, millisUntilFinished) + ":" + getCorrectTimer(false, millisUntilFinished));
        mListener.onMillisUntilFinished(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        timeInFuture = 0L;
        mTextView.setText(getCorrectTimer(true, timeInFuture) + ":" + getCorrectTimer(false, timeInFuture));

    }

    private String getCorrectTimer(boolean isMinute, long millisUntilFinished) {
        String aux;
        int constCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);

        aux = c.get(constCalendar) < 10 ? "0" + c.get(constCalendar) : "" + c.get(constCalendar);

        return (aux);
    }
}
