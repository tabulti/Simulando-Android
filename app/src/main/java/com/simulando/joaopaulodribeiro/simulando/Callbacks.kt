package com.simulando.joaopaulodribeiro.simulando

import android.view.View

import com.simulando.joaopaulodribeiro.simulando.model.simulates.AnswerTest
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

interface Callbacks {

    interface OnNotifySimulatesHomeFragmentListener {
        fun onNotifySimulatesHomeFragment(position: Int)
    }

    interface OnMillisUntilFinishedListener {
        fun onMillisUntilFinished(millisUntilFinished: Long)
    }

    interface OnTimerCountDownFinishedListener {
        fun onTimerCountDownFinished()
    }

    interface OnQuestionAnsweredListener {
        fun OnQuestionAnswered(answerTest: AnswerTest, fragmentPosition: Int)
    }

    interface OnViewPagerChangeFragmentListener {
        fun OnViewPagerChangeFragment()
    }

}
