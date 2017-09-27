package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.R;

/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

public class Home3Fragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_3, container, false);

        return view;
    }

}
