package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.R;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestFragment extends Fragment {
    private int fragmentPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_test, container, false);

        return view;
    }


    public Fragment setFragmentPosition(int fragmentPosition) {
        this.fragmentPosition = fragmentPosition;
        return this;
    }
}