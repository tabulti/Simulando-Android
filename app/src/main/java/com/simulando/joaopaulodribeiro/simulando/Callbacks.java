package com.simulando.joaopaulodribeiro.simulando;

import android.view.View;

import com.simulando.joaopaulodribeiro.simulando.model.simulates.AnswerTest;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public interface Callbacks {

    interface OnNotifySimulatesHomeFragmentListener {
        void onNotifySimulatesHomeFragment(int position);
    }

    interface OnMillisUntilFinishedListener {
        void onMillisUntilFinished(long millisUntilFinished);
    }

    interface OnQuestionAnsweredListener {
        void OnQuestionAnswered(AnswerTest answerTest, int fragmentPosition);
    }

    interface OnViewPagerChangeFragmentListener {
        void OnViewPagerChangeFragment();
    }

}
