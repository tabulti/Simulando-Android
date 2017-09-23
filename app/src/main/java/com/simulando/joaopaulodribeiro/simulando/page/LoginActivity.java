package com.simulando.joaopaulodribeiro.simulando.page;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

public class LoginActivity extends MainActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private Button mLoginBtn;
    private EditText mEmailEdt;
    private EditText mPasswordEdt;
    private RelativeLayout loading;
    private ImageView enterEmailSuccessIc;
    private ImageView enterEmailFailedIc;
    private ImageView enterPasswordSuccessIc;
    private ImageView enterPasswordFailedIc;

    private void bindViews() {
        loading = (RelativeLayout) findViewById(R.id.loading_view);

        mLoginBtn = (Button) findViewById(R.id.login_button);
        mLoginBtn.setOnClickListener(this);
        mLoginBtn.setOnFocusChangeListener(this);
        /***********************************************************/

        mEmailEdt = (EditText) findViewById(R.id.login_enter_email_edt);
        mEmailEdt.setOnClickListener(this);
        mEmailEdt.setOnFocusChangeListener(this);

        mPasswordEdt = (EditText) findViewById(R.id.login_enter_password_edt);
        mPasswordEdt.setOnClickListener(this);
        mPasswordEdt.setOnFocusChangeListener(this);
        /**********************************************************/

        enterEmailSuccessIc = (ImageView) findViewById(R.id.login_enter_email_success_ic);
        enterEmailFailedIc = (ImageView) findViewById(R.id.login_enter_email_failure_ic);

        enterPasswordSuccessIc = (ImageView) findViewById(R.id.login_enter_password_success_ic);
        enterPasswordFailedIc = (ImageView) findViewById(R.id.login_enter_password_failure_ic);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                loading.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mPasswordEdt.getWindowToken(), 0);

                if (isEnterEmailEdtValid() && isPasswordEdtValid()) {
                    final AuthStudentBody body = new AuthStudentBody(mEmailEdt.getText().toString(),
                            mPasswordEdt.getText().toString());
                    RetrofitImplementation.getInstance().AuthStudent(body, new SimulandoService.AuthStudent() {
                        @Override
                        public void onAuthStudent(AuthStudentResponse res, Error err) {
                            if (res.getStatus().equals("success")) {

                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle(res.getStatus());
                                builder.setMessage(res.getData().getStudent().toString());
                                builder.setPositiveButton("Ok", null);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loading.setVisibility(View.GONE);
                                        showProgressBar(false);
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                });
                            } else {
                                //TODO: tratar Códigos de erros do servidor
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Parece que algum dos campos contém erros", Toast.LENGTH_LONG).show();
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
}



