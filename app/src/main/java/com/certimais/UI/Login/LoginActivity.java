package com.certimais.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.certimais.Consts.LoginConsts;
import com.certimais.Interfaces.RequestCallback;
import com.certimais.UI.Dashboard.DashboardActivity;
import com.certimais.Manager.FacebookAuthManager;
import com.certimais.Manager.SessionManager;
import com.certimais.Models.User;
import com.certimais.R;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    FacebookAuthManager mFacebookAuthManager;
    SessionManager mSessionManager;

    EditText mEdtEmail;
    EditText mEdtPassword;
    LoginButton mBtnLoginFacebook;

    RequestCallback fbRequestCallback = new RequestCallback() {
        @Override
        public void onLoginSuccess(User user) {
            mSessionManager.setCurrentUser(user, LoginConsts.SESSION_FACEBOOK);
            goToDashboard();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSessionManager = SessionManager.getInstance(this);

        mFacebookAuthManager = new FacebookAuthManager();

        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLoginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);
        mFacebookAuthManager.init(mBtnLoginFacebook, fbRequestCallback);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookAuthManager.getCallback().onActivityResult(requestCode, resultCode, data);
    }

    public void goToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
