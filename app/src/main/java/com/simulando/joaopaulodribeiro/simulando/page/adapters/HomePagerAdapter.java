package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.joaopaulodribeiro.simulando.page.fragments.Home2Fragment;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.Home3Fragment;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.Home4Fragment;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.HomeStudentFragment;


/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private int mNumberOfFragments;

    public HomePagerAdapter(FragmentManager fm, int numberOfFragments) {
        super(fm);

        this.mNumberOfFragments = numberOfFragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeStudentFragment homeStudentFragment = new HomeStudentFragment();
                return homeStudentFragment;
            case 1:
                Home2Fragment home2Fragment = new Home2Fragment();
                return  home2Fragment;
            case 2:
                Home3Fragment home3Fragment = new Home3Fragment();
                return home3Fragment;
            case 3:
                Home4Fragment home4Fragment = new Home4Fragment();
                return home4Fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberOfFragments;
    }
}
