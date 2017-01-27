package com.simulando.UI.Dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.User;
import com.simulando.R;
import com.simulando.UI.Dashboard.AnswerQuestions.AnswerQuestionsActivity;
import com.simulando.UI.Dashboard.Profile.ProfileFragment;
import com.simulando.UI.Intro.IntroActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager mSessionManager;
    NavigationView mNavView;
    View mHeaderView;
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    FragmentManager mFragmentManager;

    /**
     * Sidemenu content
     */
    CircleImageView mIvSideMenuFoto;
    TextView mTvSideMenuNome;
    TextView mTvSideMenuEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mFragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            //Set Default Fragment
            ProfileFragment profileFragment = new ProfileFragment();
            mFragmentManager.beginTransaction().replace(R.id.nav_content,
                    profileFragment,
                    profileFragment.getTag()
            ).commit();
        }

        mSessionManager = SessionManager.getInstance(this);
        User user = mSessionManager.getCurrentUser();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(this);
        mHeaderView = mNavView.getHeaderView(0);

        mIvSideMenuFoto = (CircleImageView) mHeaderView.findViewById(R.id.sideMenuFoto);
        mTvSideMenuNome = (TextView) mHeaderView.findViewById(R.id.sideMenuNome);
        mTvSideMenuEmail = (TextView) mHeaderView.findViewById(R.id.sideMenuEmail);

        mTvSideMenuNome.setText(user.nome);
        mTvSideMenuEmail.setText(user.email);

        Glide.with(this)
                .load(user.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_pick_photo)
                .error(R.drawable.ic_pick_photo)
                .override(220, 220)
                .centerCrop()
                .dontAnimate()
                .into(mIvSideMenuFoto);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment currentFragment = mFragmentManager.findFragmentById(R.id.nav_content);
            if (currentFragment instanceof ProfileFragment) {
                super.onBackPressed();
            } else {
                ProfileFragment profileFragment = new ProfileFragment();
                mFragmentManager.beginTransaction().replace(R.id.nav_content,
                        profileFragment,
                        profileFragment.getTag()
                ).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        Fragment content = null;

        if (id == R.id.menu_question) {
            Intent answerQuestions = new Intent(this, AnswerQuestionsActivity.class);
            startActivity(answerQuestions);
        } else if (id == R.id.menu_performance) {

        }else if(id == R.id.menu_ranking){

        } else if (id == R.id.menu_logout) {
            mSessionManager.logout();
            goToIntro();
        }

        if (content != null) {
            mFragmentManager.beginTransaction().replace(R.id.nav_content,
                    content,
                    content.getTag()
            ).commit();
        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSessionManager.isLogged() == false) {
            goToIntro();
        }
    }

    public void goToIntro() {
        Intent loginIntent = new Intent(this, IntroActivity.class);
        startActivity(loginIntent);
    }
}
