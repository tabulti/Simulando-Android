package com.simulando.UI.Dashboard;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simulando.Adapters.ProfileTabAdapter;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.User;
import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.ExamResult.ExamResultActivity;
import com.simulando.UI.Dashboard.Questions.AnswerQuestions.AnswerQuestionsActivity;
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
     * Profile Info
     */

    CircleImageView mIvProfilePicture;
    ProgressBar mPbXp;
    TextView mTvXp;
    TextView mTvUserName;

    TabLayout mTabLayout;
    ViewPager mViewPager;
    ProfileTabAdapter mTabAdapter;

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

        mTvSideMenuNome.setText(user.name);
        mTvSideMenuEmail.setText(user.email);

        Glide.with(this)
                .load(user.profilePicture)
                .error(R.drawable.ic_student)
                .centerCrop()
                .dontAnimate()
                .into(mIvSideMenuFoto);

        /**
         * Profile Info
         */

        int max = 100;
        int current = 30;


        mTabAdapter = new ProfileTabAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mTabAdapter);

        /**
         * Realiza o Setup
         * para o tab layout
         */

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red));
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_exam);
        //mTabLayout.getTabAt(1).setIcon(R.drawable.ic_points);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_ranking);

        /**
         * Adiciona as informações do
         * usuário no painel
         */
        mIvProfilePicture = (CircleImageView) findViewById(R.id.profilePicture);
        mPbXp = (ProgressBar) findViewById(R.id.xpBar);
        mTvXp = (TextView) findViewById(R.id.xp);
        mTvUserName = (TextView) findViewById(R.id.userName);

        mPbXp.setMax(max);
        mPbXp.setProgress(current);

        String progress = getResources().getString(R.string.xp) + " " + current + "/" + max;
        mTvXp.setText(progress);

        mTvUserName.setText(user.name);
        Glide.with(this)
                .load(user.profilePicture)
                .error(R.drawable.ic_student)
                .dontAnimate()
                .centerCrop()
                .into(mIvProfilePicture);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

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

        if (id == R.id.menu_question) {
            Intent answerQuestions = new Intent(this, AnswerQuestionsActivity.class);
            startActivity(answerQuestions);
        } else if (id == R.id.menu_performance) {
            Intent perfomance = new Intent(this, ExamResultActivity.class);
            startActivity(perfomance);
        } else if (id == R.id.menu_ranking) {

        } else if (id == R.id.menu_logout) {
            mSessionManager.logout();
            goToIntro();
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
