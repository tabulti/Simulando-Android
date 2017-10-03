package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.FragmentSimulatesHomeBinding;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.FindSimulateByIdResponse;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.AnswerTestActivity;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.RecyclerSimulatesAdapter;
import com.simulando.joaopaulodribeiro.simulando.Callbacks;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatesHomeFragment extends Fragment {

    private RecyclerView mSimulatesRv;
    private List<Test> mSimulates;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(this.getContext());
    }

    private void getSimulates() {
        RetrofitImplementation.getInstance().listTests(Utils.getUserToken(this.getContext()),
                new SimulandoService.ListTests() {
            @Override
            public void onListTests(final ListSimulatesResponse res, Error err) {
                if (res != null && res.getData() != null && !res.getData().isEmpty()) {
                    mSimulates = res.getData();

                    final RecyclerSimulatesAdapter adapter = new RecyclerSimulatesAdapter(getContext(), mSimulates);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSimulatesRv.setAdapter(adapter);

                            adapter.setOnNotifySimulatesHomeFragment(new Callbacks.OnNotifySimulatesHomeFragmentListener() {
                                @Override
                                public void onNotifySimulatesHomeFragment(int position) {

                                    if (getActivity() != null) {
                                        Map<String, Test> map = new HashMap();
                                        map.put("Test", mSimulates.get(position));
                                        ((MainActivity) getActivity()).goToPage(getContext(), AnswerTestActivity.class, map);
                                    }
                                }
                            });
                            //TODO: HideLoading
                        }
                    });
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSimulatesHomeBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_simulates_home, container, false);

        mSimulatesRv = binding.simulatesRv;

        mSimulatesRv.setLayoutManager(mLayoutManager);

        //TODO:showLoading

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getSimulates();
    }

}
