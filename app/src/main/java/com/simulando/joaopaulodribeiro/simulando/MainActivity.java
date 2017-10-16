package com.simulando.joaopaulodribeiro.simulando;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.simulando.joaopaulodribeiro.simulando.databinding.LoadingViewBinding;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mLoading;
    public LoadingViewBinding mLoadingBindingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToPage(Context source, Class end) {
        Intent intent = new Intent(source, end);
        startActivity(intent);
    }

    public void goToPage(Context source, Class end, Map<String, Test> keyValue) {
        Intent intent = new Intent(source, end);

        for (Map.Entry<String, Test> entry : keyValue.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        startActivity(intent);
    }

    public void finishApp(Activity activity) {
        activity.finishAffinity();
    }

    public void showLoading(Activity activity, boolean show) {

        mLoadingBindingView = DataBindingUtil.setContentView(activity, R.layout.loading_view);
        mLoading = mLoadingBindingView.loadingView;

        if (mLoading != null) {
            if (show) {
                mLoading.setVisibility(View.VISIBLE);
            } else {
                mLoading.setVisibility(View.GONE);
            }
        }
    }
}
