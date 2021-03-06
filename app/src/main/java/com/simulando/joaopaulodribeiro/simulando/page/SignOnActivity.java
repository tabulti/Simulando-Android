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

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivitySignOnBinding;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

public class SignOnActivity extends MainActivity implements View.OnClickListener, View.OnFocusChangeListener,
        TextView.OnEditorActionListener {
    private ActivitySignOnBinding mBinding;

    private Button mRegisterBtn;
    private EditText mEmailEdt;
    private EditText mPasswordEdt;
    private EditText mConfirmPasswordEdt;
    private ImageView enterEmailSuccessIc;
    private ImageView enterEmailFailedIc;
    private ImageView enterPasswordSuccessIc;
    private ImageView enterPasswordFailedIc;
    private ImageView confirmPasswordSuccessIc;
    private ImageView confirmPasswordFailedIc;

    private void bindViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_on);

        mRegisterBtn = mBinding.registerButton;
        mRegisterBtn.setOnClickListener(this);
        mRegisterBtn.setOnFocusChangeListener(this);

        mEmailEdt = mBinding.enterEmailEdt;
        mEmailEdt.setOnClickListener(this);
        mEmailEdt.setOnFocusChangeListener(this);

        mPasswordEdt = mBinding.enterPasswordEdt;
        mPasswordEdt.setOnClickListener(this);
        mPasswordEdt.setOnFocusChangeListener(this);

        mConfirmPasswordEdt = mBinding.confirmPasswordEdt;
        mConfirmPasswordEdt.setOnClickListener(this);
        mConfirmPasswordEdt.setOnFocusChangeListener(this);

        mConfirmPasswordEdt.setOnEditorActionListener(this);

        enterEmailSuccessIc = mBinding.enterEmailSuccessIc;
        enterEmailFailedIc = mBinding.enterEmailFailureIc;

        enterPasswordSuccessIc = mBinding.enterPasswordSuccessIc;
        enterPasswordFailedIc = mBinding.enterPasswordFailureIc;

        confirmPasswordSuccessIc = mBinding.confirmPasswordSuccessIc;
        confirmPasswordFailedIc = mBinding.confirmPasswordFailureIc;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register_button:

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mConfirmPasswordEdt.getWindowToken(), 0);

                if (isEnterEmailEdtValid() && isPasswordEdtValid() && isConfirmPasswordValid()) {
                    showLoading(SignOnActivity.this, true);
                    final RegisterStudentBody body = new RegisterStudentBody(mPasswordEdt.getText().toString(),
                            mEmailEdt.getText().toString(), mConfirmPasswordEdt.getText().toString());
                    RetrofitImplementation.getInstance().registerStudent(body, new SimulandoService.RegisterStudent() {
                        @Override
                        public void onRegisterStudent(RegisterStudentResponse res, Error err) {
                            if (res.getStatus().equals("success")) {

                                if (res.getToken() != null && !res.getToken().isEmpty()) {
                                    Utils.saveUserToken(SignOnActivity.this, res.getToken());
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showLoading(SignOnActivity.this, false);
                                    }
                                });

                                goToPage(SignOnActivity.this, HomeActivity.class);
                            }
                        }
                    });
                } else {
                    Utils.showErrorSnackBar(mRegisterBtn, R.string.fields_erros_msg);
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {

            case R.id.enter_email_edt:
                if (!hasFocus) {
                    isEnterEmailEdtValid();
                }
                break;

            case R.id.enter_password_edt:
                if (!hasFocus) {
                    isPasswordEdtValid();
                }
                break;

            case R.id.confirm_password_edt:
                if (!hasFocus) {
                    isConfirmPasswordValid();
                }
                break;
        }
    }

    private boolean isEnterEmailEdtValid() {
        if (Utils.isValidEmail(mEmailEdt.getText().toString())) {
            showSuccessIcon(enterEmailSuccessIc, enterEmailFailedIc, true);
            return true;
        } else {
            showSuccessIcon(enterEmailSuccessIc, enterEmailFailedIc, false);
            return false;
        }
    }

    public boolean isConfirmPasswordValid() {
        if (!TextUtils.isEmpty(mConfirmPasswordEdt.getText())) {
            if (!mConfirmPasswordEdt.getText().toString().equals(mPasswordEdt.getText().toString())) {
                showSuccessIcon(confirmPasswordSuccessIc, confirmPasswordFailedIc, false);
                return false;
            } else {
                showSuccessIcon(confirmPasswordSuccessIc, confirmPasswordFailedIc, true);
                return true;
            }
        } else if (TextUtils.isEmpty(mConfirmPasswordEdt.getText()) && !TextUtils.isEmpty(mPasswordEdt.getText())) {
            showSuccessIcon(confirmPasswordSuccessIc, confirmPasswordFailedIc, false);
            return false;
        }
        return false;
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
            mRegisterBtn.performClick();
        }
        return false;
    }
}
