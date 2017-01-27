package com.simulando.UI.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simulando.Manager.SessionManager;
import com.simulando.UI.Intro.IntroActivity;
import com.simulando.Utils.AppUtils;
import com.facebook.FacebookSdk;

/**
 * Created by Luciano Junior on 04,December,2016
 */
public class SplashActivity extends AppCompatActivity {

    SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        mSessionManager = SessionManager.getInstance(this);
        if (mSessionManager.isLogged() == true) {
            AppUtils.goToDashboard(this);
        }else{
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
            finish();
        }
    }
}