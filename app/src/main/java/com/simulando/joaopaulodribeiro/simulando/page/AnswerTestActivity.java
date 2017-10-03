package com.simulando.joaopaulodribeiro.simulando.page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivityAnswerTestBinding;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.AnswerTestAdapter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestActivity extends MainActivity implements Serializable{

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private HashMap bundleMap;
    private Test mTest;

    private void bindViews() {
        ActivityAnswerTestBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_answer_test);

        AnswerTestAdapter adapter = new AnswerTestAdapter(getSupportFragmentManager(), mTest);

        mToolbar = binding.toolbarAnswerTest;
        mViewPager = binding.answerTestViewPager;

        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(48);

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);

        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Test extras = (Test) getIntent().getSerializableExtra("Test");


        bindViews();

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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
