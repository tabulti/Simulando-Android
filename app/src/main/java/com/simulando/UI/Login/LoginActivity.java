package com.simulando.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.simulando.API.User.UserService;
import com.simulando.Consts.LoginConsts;
import com.simulando.Interfaces.APICallback;
import com.simulando.Interfaces.RequestCallback;
import com.simulando.Manager.FacebookAuthManager;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.Session;
import com.simulando.Models.User;
import com.simulando.Models.UserAuthInfo;
import com.simulando.R;
import com.simulando.Utils.AppUtils;
import com.simulando.Utils.CommonUtils;
import com.simulando.Utils.NetworkUtils;

public class LoginActivity extends AppCompatActivity {

    FacebookAuthManager mFacebookAuthManager;
    SessionManager mSessionManager;
    UserService mUserService;

    EditText mEdtEmail;
    EditText mEdtPassword;
    Button mBtnLoginFacebook;
    Button mBtnLoginGoogle;
    Button mBtnLoginEmail;

    RequestCallback fbRequestCallback = new RequestCallback() {
        @Override
        public void onLoginSuccess(User user) {
            CommonUtils.showLoadingDialog(LoginActivity.this);
            UserAuthInfo userInfo = new UserAuthInfo(true, user.nome, "", user.email, user.id, user.photo);
            Log.d("user", new Gson().toJson(user));
            mUserService.authUser(userInfo, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    Session session = (Session) response;
                    mSessionManager.setCurrentUser(session.user, LoginConsts.SESSION_FACEBOOK, session.token);
                    AppUtils.goToDashboard(LoginActivity.this);
                }

                @Override
                public void onError(String message) {

                }
            });
        }

    };

    View.OnClickListener mLoginBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            boolean hasConnection = NetworkUtils.isNetworkAvailable(LoginActivity.this);

            if (!hasConnection) {
                AppUtils.showToast(getApplicationContext(), R.string.no_connection, Toast.LENGTH_SHORT);
                return;
            }

            if (id == R.id.btnLoginFacebook) {
                mFacebookAuthManager.auth(LoginActivity.this);
            } else if (id == R.id.btnLoginGoogle) {
                //Auth With Google
            } else if (id == R.id.btnLoginEmail) {
                auth();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSessionManager = SessionManager.getInstance(this);

        mFacebookAuthManager = new FacebookAuthManager();
        mUserService = UserService.getInstance(this);

        mEdtEmail = (EditText) findViewById(R.id.email);
        mEdtPassword = (EditText) findViewById(R.id.password);
        mBtnLoginEmail = (Button) findViewById(R.id.btnLoginEmail);
        mBtnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);
        mBtnLoginGoogle = (Button) findViewById(R.id.btnLoginGoogle);

        mBtnLoginEmail.setOnClickListener(mLoginBtnListener);
        mBtnLoginFacebook.setOnClickListener(mLoginBtnListener);
        mFacebookAuthManager.init(fbRequestCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookAuthManager.getCallback().onActivityResult(requestCode, resultCode, data);
    }

    public void auth() {
        CommonUtils.showLoadingDialog(this);

        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();

        UserAuthInfo authInfo = new UserAuthInfo(false, "", "", email, password, "");

        mUserService.authUser(authInfo, new APICallback() {
            @Override
            public void onSuccess(Object response) {
                Session session = (Session) response;
                mSessionManager.setCurrentUser(session.user, LoginConsts.SESSION_LOGIN_API, session.token);
                AppUtils.goToDashboard(LoginActivity.this);
            }

            @Override
            public void onError(String message) {
                CommonUtils.hideDialog();


            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        CommonUtils.hideDialog();
    }


}
