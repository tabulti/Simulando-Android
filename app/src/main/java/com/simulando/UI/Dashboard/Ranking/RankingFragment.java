package com.simulando.UI.Dashboard.Ranking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.simulando.API.User.StudentService;
import com.simulando.Adapters.RankingAdapter;
import com.simulando.Interfaces.Callback;
import com.simulando.Models.Ranking;
import com.simulando.Models.RankingRow;
import com.simulando.R;

import java.util.ArrayList;


public class RankingFragment extends Fragment {

    StudentService mStudentService;

    ListView mRankingList;
    RankingAdapter mRankingAdapter;
    Ranking mRanking;


    public RankingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        mStudentService = StudentService.getInstance(getContext());
        mRanking = new Ranking();
        getRanking();

        mRankingAdapter = new RankingAdapter(getContext(), mRanking.rows);
        mRankingList = (ListView) view.findViewById(R.id.ranking);

        mRankingList.setAdapter(mRankingAdapter);

        return view;
    }

    /**
     * Busca o ranking de alunos
     */
    public void getRanking() {
        mStudentService.getRank(new Callback() {
            @Override
            public void onSuccess(Object response) {
                mRanking.rows = (ArrayList<RankingRow>) response;
                mRankingAdapter.updateList(mRanking.rows);
                mRankingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

}
