package com.certimais.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.certimais.API.User.UserService;
import com.certimais.Consts.LoginConsts;
import com.certimais.Interfaces.APICallback;
import com.certimais.Interfaces.RequestCallback;
import com.certimais.Manager.FacebookAuthManager;
import com.certimais.Manager.SessionManager;
import com.certimais.Models.Session;
import com.certimais.Models.User;
import com.certimais.R;
import com.certimais.UI.Dashboard.DashboardActivity;
import com.certimais.Utils.AppUtils;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    FacebookAuthManager mFacebookAuthManager;
    SessionManager mSessionManager;
    UserService mUserService;

    EditText mEdtEmail;
    EditText mEdtPassword;
    LoginButton mBtnLoginFacebook;
    Button mBtnLogin;

    RequestCallback fbRequestCallback = new RequestCallback() {
        @Override
        public void onLoginSuccess(User user) {
            mSessionManager.setCurrentUser(user, LoginConsts.SESSION_FACEBOOK, "");
            goToDashboard();
        }

    };

    View.OnClickListener loginEmailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            auth();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSessionManager = SessionManager.getInstance(this);

        mFacebookAuthManager = new FacebookAuthManager();
        mUserService = UserService.getInstance(this);

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

    public void auth() {
        AppUtils.showLoadingDialog(this);
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            mUserService.authUser(email, password, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    Session session = (Session) response;
                    mSessionManager.setCurrentUser(session.user, LoginConsts.SESSION_LOGIN_API, session.token);
                    AppUtils.hideDialog();
                    goToDashboard();
                }

                @Override
                public void onError(String message) {
                    AppUtils.hideDialog();
                }
            });
        }
    }

    public void goToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
