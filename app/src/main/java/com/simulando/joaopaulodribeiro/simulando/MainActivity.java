package com.simulando.joaopaulodribeiro.simulando;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.page.SignOnActivity;
import com.simulando.joaopaulodribeiro.simulando.page.SplashActivity;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;

public class MainActivity extends AppCompatActivity{

    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loading_view);

    }

    public void goToPage(Context source, Class end) {
        Intent intent = new Intent(source, end);
        startActivity(intent);
    }

    public void finishApp(Activity activity) {
        activity.finishAffinity();
    }

    public void showProgressBar(boolean show) {
        if (loading != null) {
            if (show) {
                loading.setVisibility(View.VISIBLE);
            } else {
                loading.setVisibility(View.GONE);
            }
        }
    }
}
