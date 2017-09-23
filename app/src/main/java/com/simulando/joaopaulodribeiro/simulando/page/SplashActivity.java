package com.simulando.joaopaulodribeiro.simulando.page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;

public class SplashActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RetrofitImplementation.getInstance().initRetrofit();


        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToPage(SplashActivity.this, WelcomeActivity.class);
            }
        }, 2000);
    }
}
