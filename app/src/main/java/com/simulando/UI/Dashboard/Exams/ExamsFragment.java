package com.simulando.UI.Dashboard.Exams;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.Adapters.ExamsListAdapter;
import com.simulando.Models.Exam;
import com.simulando.R;

import java.util.ArrayList;


public class ExamsFragment extends Fragment {


    RecyclerView mRecyclerView;
    ArrayList<Exam> mExamsList;
    ExamsListAdapter mAdapter;

    public ExamsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exams, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.exams);
        mRecyclerView.setHasFixedSize(true);

        mExamsList = new ArrayList<>();
        generateItem();
        mAdapter = new ExamsListAdapter(getActivity(), mExamsList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (mExamsList.size() > 0 && mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    public void generateItem() {
        for (int i = 0; i < 10; i++) {
            mExamsList.add(new Exam((i + 1) + "º Simulado"));
        }
    }

}
