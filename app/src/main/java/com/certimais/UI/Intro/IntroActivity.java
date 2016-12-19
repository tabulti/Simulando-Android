package com.certimais.UI.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.certimais.Manager.SessionManager;
import com.certimais.R;
import com.certimais.UI.Dashboard.DashboardActivity;
import com.certimais.UI.Login.LoginActivity;
import com.certimais.UI.Register.RegisterActivity;

public class IntroActivity extends AppCompatActivity {

    SessionManager mSessionManager;

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

        mSessionManager = SessionManager.getInstance(this);
        if(mSessionManager.isLogged() == true){
            goToDashboard();
        }

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);

        mBtnLogin.setOnClickListener(loginListener);
        mBtnRegister.setOnClickListener(registerListener);

    }

    public void goToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}

