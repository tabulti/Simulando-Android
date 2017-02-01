package com.simulando.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.ExamsFragment;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class ProfileTabAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    final int PAGES_COUNT = 3;
    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
    private int[] imageResId = {R.drawable.ic_correct_answer, R.drawable.ic_correct_answer, R.drawable.ic_correct_answer};

    public ProfileTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return new ExamsFragment();
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

}
