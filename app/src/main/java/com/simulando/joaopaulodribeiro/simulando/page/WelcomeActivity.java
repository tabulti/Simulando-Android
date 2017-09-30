package com.simulando.joaopaulodribeiro.simulando.page;

import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivityWelcomeBinding;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;

public class WelcomeActivity extends MainActivity implements View.OnClickListener{

    private Button mSignInBtn;
    private Button mSignOnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWelcomeBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        mSignInBtn = mBinding.signInBtn;
        mSignOnBtn = mBinding.signOnBtn;

        mSignOnBtn.setOnClickListener(this);
        mSignInBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishApp(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_on_btn:
                goToPage(WelcomeActivity.this, SignOnActivity.class);
                break;
            case R.id.sign_in_btn:
                goToPage(WelcomeActivity.this, LoginActivity.class);
                break;
        }
    }
}
