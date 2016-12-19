package com.certimais.UI.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.certimais.UI.Intro.IntroActivity;
import com.certimais.UI.Login.LoginActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Luciano Junior on 04,December,2016
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}