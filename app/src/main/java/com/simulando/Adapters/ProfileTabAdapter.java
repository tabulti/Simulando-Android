package com.simulando.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simulando.R;
import com.simulando.UI.Dashboard.Achievements.AchievementFragment;
import com.simulando.UI.Dashboard.Exams.ExamsFragment;
import com.simulando.UI.Dashboard.Ranking.RankingFragment;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class ProfileTabAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    final int PAGES_COUNT = 3;

    public ProfileTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ExamsFragment();
        } else if (position == 1) {
            return new AchievementFragment();
        } else {
            return new RankingFragment();
        }

    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

}
