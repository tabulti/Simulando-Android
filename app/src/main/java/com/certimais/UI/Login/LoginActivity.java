package com.certimais.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button mBtnLogin;

    RequestCallback fbRequestCallback = new RequestCallback() {
        @Override
        public void onLoginSuccess(User user) {
            mSessionManager.setCurrentUser(user, LoginConsts.SESSION_FACEBOOK);
            goToDashboard();
        }

    };

    View.OnClickListener loginEmailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            logar();
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
        mBtnLogin = (Button) findViewById(R.id.btnLoginEmail);

        mBtnLogin.setOnClickListener(loginEmailListener);
        mBtnLoginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);
        mFacebookAuthManager.init(mBtnLoginFacebook, fbRequestCallback);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookAuthManager.getCallback().onActivityResult(requestCode, resultCode, data);
    }

    public void logar() {
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        User user = new User();
        user.email=email;
        user.nome = email;

        Log.d("AUTH_INFO", "EM: " + email + ", Senha: " + password);
        if (!email.isEmpty() && !password.isEmpty()) {
            mSessionManager.setCurrentUser(user, LoginConsts.SESSION_LOGIN_API);
            goToDashboard();
        }
    }

    public void goToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
