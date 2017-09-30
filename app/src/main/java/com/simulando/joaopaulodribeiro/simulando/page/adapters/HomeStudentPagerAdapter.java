package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.joaopaulodribeiro.simulando.page.fragments.DisciplinesFragment;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.SimulatesHomeFragment;

/**
 * Created by joao.paulo.d.ribeiro on 26/09/2017.
 */

public class HomeStudentPagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public HomeStudentPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SimulatesHomeFragment simulatesHomeFragment = new SimulatesHomeFragment();
                return simulatesHomeFragment;
            case 1:
                DisciplinesFragment disciplinesFragment = new DisciplinesFragment();
                return disciplinesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
