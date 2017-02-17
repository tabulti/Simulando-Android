package com.simulando.UI.Dashboard.Exams;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.simulando.API.Exam.ExamService;
import com.simulando.Adapters.ExamsListAdapter;
import com.simulando.Interfaces.APICallback;
import com.simulando.Models.Exam;
import com.simulando.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ExamsListFragment extends Fragment {

    ExamService mExamService;
    SwipeRefreshLayout mExamsListRefresher;

    RecyclerView mRecyclerView;
    ArrayList<Exam> mExamsList;
    ExamsListAdapter mAdapter;

    public ExamsListFragment() {
    }

    SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getExams();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exams, container, false);

        mExamService = ExamService.getInstance(getActivity());

        mExamsListRefresher = (SwipeRefreshLayout) view.findViewById(R.id.examsListRefresher);
        mExamsListRefresher.setColorSchemeResources(R.color.colorAccent);
        mExamsListRefresher.setOnRefreshListener(mRefreshListener);

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
                mExamsListRefresher.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

}
