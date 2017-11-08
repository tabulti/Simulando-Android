package com.simulando.joaopaulodribeiro.simulando.page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.model.student.RefreshTokenResponse;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

public class SplashActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RetrofitImplementation.Companion.getInstance().initRetrofit();


        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

            verifyUserToken();

            }
        }, 1000);
    }

    public void verifyUserToken() {
        final String userToken = Utils.getUserToken(this);
        if (userToken.equals("")) {
            goToPage(SplashActivity.this, WelcomeActivity.class);
        } else {
            RetrofitImplementation.Companion.getInstance().refreshToken(userToken, new SimulandoService.RefreshToken() {
                @Override
                public void onRefreshToken(RefreshTokenResponse res, Error err) {
                    if (res.getToken().equals(userToken)) {
                        goToPage(SplashActivity.this, HomeActivity.class);
                    } else {
                        goToPage(SplashActivity.this, WelcomeActivity.class);
                    }
                }
            });
        }
    }
}
