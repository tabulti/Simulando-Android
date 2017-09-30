package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.FragmentSimulatesHomeBinding;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.RecyclerSimulatesAdapter;
import com.simulando.joaopaulodribeiro.simulando.retrofit.RetrofitImplementation;
import com.simulando.joaopaulodribeiro.simulando.retrofit.SimulandoService;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import java.util.List;

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
        RetrofitImplementation.getInstance().ListTests(Utils.getUserToken(this.getContext()),
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String action);
    }
}
