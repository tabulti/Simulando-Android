package com.simulando.joaopaulodribeiro.simulando;

import android.view.View;

import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public interface Callbacks {

    interface OnNotifySimulatesHomeFragmentListener {
        void onNotifySimulatesHomeFragment(int position);
    }

    interface OnNotifyHomeStudentPageAdapterListener {
        void onNotifyHomeStudentPageAdapter(Test test);
    }

    interface testeBackToHomeStudentFragmentListener {
        void onBackToHomeStudentFragment(Test test);
    }

    interface OnNotifyBackToHomePageAdapterListener {
        void onNotifyBackToHomePageAdapter(Test test);
    }

    interface OnNotifyBackToHomeActivityListener {
        void onNotifyBackToHomeActivity(Test test);
    }
}
