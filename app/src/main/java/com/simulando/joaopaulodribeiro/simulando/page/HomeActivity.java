package com.simulando.joaopaulodribeiro.simulando.page;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivityHomeBinding;
import com.simulando.joaopaulodribeiro.simulando.Callbacks;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.HomePagerAdapter;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.Home2Fragment;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends MainActivity implements
        Home2Fragment.OnFragmentInteractionListener{

    private Toolbar mToolbar;
    private TabLayout mBottommTabHomeLayout;
    private ViewPager mViewPager;
    private ActivityHomeBinding mBinding;



    private void bindViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        mToolbar = mBinding.toolbarMain;
        mBottommTabHomeLayout = mBinding.bottomToolbar;
        mViewPager = mBinding.newHomeViewPager;

        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();

        mBottommTabHomeLayout.addTab(mBottommTabHomeLayout.newTab().setIcon(R.drawable.home_student));
        mBottommTabHomeLayout.addTab(mBottommTabHomeLayout.newTab().setIcon(R.drawable.trending_up_disable));
        mBottommTabHomeLayout.addTab(mBottommTabHomeLayout.newTab().setIcon(R.drawable.school_disable));
        mBottommTabHomeLayout.addTab(mBottommTabHomeLayout.newTab().setIcon(R.drawable.certificate_disable));

        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), mBottommTabHomeLayout.getTabCount());

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBottommTabHomeLayout));
        mBottommTabHomeLayout.addOnTabSelectedListener(getTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_notifications:
                Toast.makeText(this, R.string.notifications_selected, Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_exit_app:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.atention);
                builder.setMessage(R.string.logout_alert_msg);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.saveUserToken(HomeActivity.this, "");
                        goToPage(HomeActivity.this, WelcomeActivity.class);
                    }
                });
                builder.setNegativeButton(R.string.no, null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishApp(HomeActivity.this);
    }

    @NonNull
    private TabLayout.OnTabSelectedListener getTabSelectedListener(final ViewPager viewPager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setBottomTabIcon(tab, true);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setBottomTabIcon(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    public void setBottomTabIcon(TabLayout.Tab tab, boolean enable) {
        switch (tab.getPosition()) {
            case 0:
                if (enable) {
                    tab.setIcon(R.drawable.home_student);
                } else {
                    tab.setIcon(R.drawable.home_student_disable);
                }
                break;
            case 1:
                if (enable) {
                    tab.setIcon(R.drawable.trending_up);
                } else {
                    tab.setIcon(R.drawable.trending_up_disable);
                }
                break;
            case 2:
                if (enable) {
                    tab.setIcon(R.drawable.school);
                } else {
                    tab.setIcon(R.drawable.school_disable);
                }
                break;
            case 3:
                if (enable) {
                    tab.setIcon(R.drawable.certificate);
                } else {
                    tab.setIcon(R.drawable.certificate_disable);
                }
                 break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
