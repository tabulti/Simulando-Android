package com.simulando.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.simulando.Models.Exam;
import com.simulando.R;

import java.util.ArrayList;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class ExamsListAdapter extends RecyclerView.Adapter<ExamsListAdapter.ExamsViewHolder> {

    private ArrayList<Exam> mExamsList;
    private Context mContext;

    public ExamsListAdapter(Context context, ArrayList<Exam> examsList) {
        mExamsList = examsList;
        mContext = context;
    }

    @Override
    public ExamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exams_list_item, parent, false);

        ExamsViewHolder holder = new ExamsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ExamsViewHolder holder, int position) {
        Exam exam = mExamsList.get(position);
        holder.mTvTitle.setText(exam.title);
    }

    @Override
    public int getItemCount() {
        return mExamsList.size();
    }

    public class ExamsViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        TextView mTvDate;
        Button mBtnStartExam;

        public ExamsViewHolder(View view) {
            super(view);

            mTvTitle = (TextView) view.findViewById(R.id.examTitle);
            mTvDate = (TextView) view.findViewById(R.id.examDate);
            mBtnStartExam = (Button) view.findViewById(R.id.startButton);

        }
    }

}
