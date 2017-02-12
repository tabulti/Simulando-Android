package com.simulando.UI.Dashboard.Ranking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.simulando.Adapters.RankingAdapter;
import com.simulando.Models.RankItem;
import com.simulando.R;

import java.util.ArrayList;


public class RankingFragment extends Fragment {


    ListView mRankingList;
    RankingAdapter mRankingAdapter;
    ArrayList<RankItem> mRankItems;


    public RankingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        init();

        mRankingAdapter = new RankingAdapter(getContext(), mRankItems);
        mRankingList = (ListView) view.findViewById(R.id.ranking);

        mRankingList.setAdapter(mRankingAdapter);

        return view;
    }


    public void init() {
        mRankItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mRankItems.add(new RankItem(i, "", "Luciano", "780"));
        }
    }

}
