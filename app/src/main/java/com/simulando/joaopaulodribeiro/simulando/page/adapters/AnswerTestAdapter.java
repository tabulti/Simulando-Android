package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.Callbacks;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.AnswerTest;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.AnswerTestFragment;

import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestAdapter extends FragmentStatePagerAdapter {
    private Test mTest;
    private Callbacks.OnQuestionAnsweredListener mListener;

    public AnswerTestAdapter(FragmentManager fm, Test test, Callbacks.OnQuestionAnsweredListener listener) {
        super(fm);
        this.mTest = test;
        this.mListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        AnswerTestFragment fragment = new AnswerTestFragment();
        return fragment.setFragmentPosition(position, mTest, mListener);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.95f;
    }

    @Override
    public int getCount() {
        return mTest.getQuestions().size();
    }
}
