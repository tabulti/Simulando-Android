package com.simulando.UI.Dashboard.Exams;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simulando.API.Exam.ExamService;
import com.simulando.Adapters.ExamsListAdapter;
import com.simulando.Interfaces.APICallback;
import com.simulando.Models.Exam;
import com.simulando.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ExamsFragment extends Fragment {

    ExamService mExamService;

    RecyclerView mRecyclerView;
    ArrayList<Exam> mExamsList;
    ExamsListAdapter mAdapter;

    public ExamsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exams, container, false);

        mExamService = ExamService.getInstance(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.exams);
        mRecyclerView.setHasFixedSize(true);
        mExamsList = new ArrayList<>();

        getExams();

        mAdapter = new ExamsListAdapter(getActivity(), mExamsList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    public void getExams() {
        mExamService.getExams(new APICallback() {
            @Override
            public void onSuccess(Object response) {
                mExamsList = (ArrayList<Exam>) response;
                mAdapter.updateList(mExamsList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

}
