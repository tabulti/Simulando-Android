package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.joaopaulodribeiro.simulando.page.fragments.DiciplinesFragment;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.SimulatesHomeFragment;

/**
 * Created by joao.paulo.d.ribeiro on 26/09/2017.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public HomePagerAdapter(FragmentManager fm, int numberOfTabs) {
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
                DiciplinesFragment diciplinesFragment = new DiciplinesFragment();
                return diciplinesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
