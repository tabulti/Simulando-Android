package com.simulando.UI.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simulando.R;
import com.simulando.UI.Login.LoginActivity;
import com.simulando.UI.Register.RegisterActivity;

public class IntroActivity extends AppCompatActivity {

    Button mBtnLogin;
    Button mBtnRegister;

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent loginIntent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    };

    View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent loginIntent = new Intent(IntroActivity.this, RegisterActivity.class);
            startActivity(loginIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mBtnLogin = (Button) findViewById(R.id.btnLoginIntro);
        mBtnRegister = (Button) findViewById(R.id.btnRegisterIntro);

        mBtnLogin.setOnClickListener(loginListener);
        mBtnRegister.setOnClickListener(registerListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}

