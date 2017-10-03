package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.AnswerTestFragment;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestAdapter extends FragmentStatePagerAdapter {
    private Test mTest;

    public AnswerTestAdapter(FragmentManager fm, Test test) {
        super(fm);

        this.mTest = test;
    }

    @Override
    public Fragment getItem(int position) {
        return new AnswerTestFragment().setFragmentPosition(position);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.95f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
