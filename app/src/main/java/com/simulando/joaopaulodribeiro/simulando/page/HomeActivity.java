package com.simulando.joaopaulodribeiro.simulando.page;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.HomePagerAdapter;
import com.simulando.joaopaulodribeiro.simulando.page.fragments.SimulatesHomeFragment;

public class HomeActivity extends MainActivity implements SimulatesHomeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TabLayout tabHomeLayout = (TabLayout) findViewById(R.id.tab_home);
        tabHomeLayout.addTab(tabHomeLayout.newTab().setText(R.string.tab_1_name));
        tabHomeLayout.addTab(tabHomeLayout.newTab().setText(R.string.tab_2_name));
        tabHomeLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.home_scroll_view);
        scrollView.setFillViewport(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.home_view_pager);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), tabHomeLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabHomeLayout));
        tabHomeLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
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
                Toast.makeText(this, "Notifications selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
