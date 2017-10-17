package com.simulando.joaopaulodribeiro.simulando.page;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivityLoginBinding;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import okhttp3.internal.Util;

public class LoginActivity extends MainActivity implements View.OnClickListener, View.OnFocusChangeListener,
        TextView.OnEditorActionListener {

    private ActivityLoginBinding mBinding;

    private Button mLoginBtn;
    private EditText mEmailEdt;
    private EditText mPasswordEdt;
    private ImageView enterEmailSuccessIc;
    private ImageView enterEmailFailedIc;
    private ImageView enterPasswordSuccessIc;
    private ImageView enterPasswordFailedIc;

    private void bindViews() {

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoginBtn = mBinding.loginButton;
        mLoginBtn.setOnClickListener(this);
        mLoginBtn.setOnFocusChangeListener(this);

        mEmailEdt = mBinding.loginEnterEmailEdt;
        mEmailEdt.setOnClickListener(this);
        mEmailEdt.setOnFocusChangeListener(this);

        mPasswordEdt = mBinding.loginEnterPasswordEdt;
        mPasswordEdt.setOnClickListener(this);
        mPasswordEdt.setOnFocusChangeListener(this);

        mPasswordEdt.setOnEditorActionListener(this);

        enterEmailSuccessIc = mBinding.loginEnterEmailSuccessIc;
        enterEmailFailedIc = mBinding.loginEnterEmailFailureIc;

        enterPasswordSuccessIc = mBinding.loginEnterPasswordSuccessIc;
        enterPasswordFailedIc = mBinding.loginEnterPasswordFailureIc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mPasswordEdt.getWindowToken(), 0);

                if (isEnterEmailEdtValid() && isPasswordEdtValid()) {
                    showLoading(LoginActivity.this, true);
                    final AuthStudentBody body = new AuthStudentBody(mEmailEdt.getText().toString(),
                            mPasswordEdt.getText().toString());
                    RetrofitImplementation.getInstance().authStudent(body, new SimulandoService.AuthStudent() {
                        @Override
                        public void onAuthStudent(AuthStudentResponse res, Error err) {
                            if (res != null) {
                                if (res.getStatus().equals("success")) {

                                    if (res.getToken() != null && !res.getToken().isEmpty()) {
                                        Utils.saveUserToken(LoginActivity.this, res.getToken());
                                    }

                                    goToPage(LoginActivity.this, HomeActivity.class);
                                } else {
                                    //TODO: tratar Códigos de erros do servidor
                                }
                            }
                        }
                    });
                } else {
                    Utils.showErrorSnackBar(mLoginBtn, R.string.fields_erros_msg);
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {

            case R.id.login_enter_email_edt:
                if (!hasFocus) {
                    isEnterEmailEdtValid();
                }
                break;

            case R.id.login_enter_password_edt:
                if(!hasFocus) {
                    isPasswordEdtValid();
                }
                break;
        }
    }

    private boolean isEnterEmailEdtValid() {
        if (Utils.isValidEmail(mEmailEdt.getText().toString())){
            showSuccessIcon(enterEmailSuccessIc, enterEmailFailedIc, true);
            return true;
        } else {
            showSuccessIcon(enterEmailSuccessIc, enterEmailFailedIc, false);
            return false;
        }
    }

    public boolean isPasswordEdtValid() {
        if (TextUtils.isEmpty(mPasswordEdt.getText())) {
            showSuccessIcon(enterPasswordSuccessIc, enterPasswordFailedIc, false);
            return false;
        } else {
            showSuccessIcon(enterPasswordSuccessIc, enterPasswordFailedIc, true);
            return true;
        }
    }

    public void showSuccessIcon(ImageView successIc, ImageView failureIcon, boolean show) {
        if (show) {
            successIc.setVisibility(View.VISIBLE);
            failureIcon.setVisibility(View.GONE);
        } else {
            successIc.setVisibility(View.GONE);
            failureIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            mLoginBtn.performClick();
        }
        return false;
    }
}



