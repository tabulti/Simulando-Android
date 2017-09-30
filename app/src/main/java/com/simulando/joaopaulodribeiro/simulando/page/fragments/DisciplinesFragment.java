package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.FragmentDiciplinesBinding;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.RecyclerDisciplinesAdapter;

/**
 * Created by joao.paulo.d.ribeiro on 26/09/2017.
 */

public class DisciplinesFragment extends Fragment {

    private FragmentDiciplinesBinding mBinding;
    private RecyclerView mCardsRv;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diciplines, container, false);

        mCardsRv = mBinding.diciplinesRv;

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mCardsRv.setLayoutManager(mLayoutManager);

        RecyclerDisciplinesAdapter adapter = new RecyclerDisciplinesAdapter(this.getContext());

        mCardsRv.setAdapter(adapter);

        return mBinding.getRoot();
    }

}
