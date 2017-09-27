package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.FragmentStudentHomeBinding;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.HomeStudentPagerAdapter;

/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

public class HomeStudentFragment extends Fragment {

    private FragmentStudentHomeBinding mBinding;

    private TabLayout mTabHomeLayout;
    private NestedScrollView mScrollView;
    private ViewPager mViewPager;

    private void bindViews() {
        mTabHomeLayout = mBinding.tabHome;
        mScrollView = mBinding.homeScrollView;
        mViewPager = mBinding.newHomeStudentViewPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_home, container, false);

        View view = mBinding.getRoot();

        bindViews();

        mTabHomeLayout.addTab(mTabHomeLayout.newTab().setText(R.string.tab_1_name));
        mTabHomeLayout.addTab(mTabHomeLayout.newTab().setText(R.string.tab_2_name));
        mTabHomeLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mScrollView.setFillViewport(true);

        HomeStudentPagerAdapter adapter = new HomeStudentPagerAdapter(getChildFragmentManager(), mTabHomeLayout.getTabCount());

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabHomeLayout));
        mTabHomeLayout.addOnTabSelectedListener(getTabSelectedListener(mViewPager));

        return view;
    }

    @NonNull
    private TabLayout.OnTabSelectedListener getTabSelectedListener(final ViewPager viewPager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }
}
