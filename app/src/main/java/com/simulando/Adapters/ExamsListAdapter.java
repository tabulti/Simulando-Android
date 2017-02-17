package com.simulando.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simulando.Models.Exam;
import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.AnswerExamActivity;
import com.simulando.Utils.AppUtils;

import org.parceler.Parcels;
import org.w3c.dom.Text;

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

    public void updateList(ArrayList<Exam> examsList) {
        this.mExamsList.clear();
        this.mExamsList.addAll(examsList);
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

        final Exam exam = mExamsList.get(position);
        String exam_date = mContext.getResources().getString(R.string.available_date) + " " +
                AppUtils.getExamDate(exam.exam_start_date, exam.exam_end_date);
        boolean isAvailable = AppUtils.isExamAvailable(exam.exam_start_date, exam.exam_end_date);

        if (!isAvailable) {
            holder.mExamCard.setBackgroundColor(Color.GRAY);
            holder.mBtnStartExam.setVisibility(View.GONE);
        }else{
            holder.mExamCard.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.mBtnStartExam.setVisibility(View.VISIBLE);
        }

        holder.mBtnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answerExam = new Intent(mContext, AnswerExamActivity.class);
                answerExam.putExtra("examId", exam.id);
                mContext.startActivity(answerExam);
            }
        });
        holder.mTvTitle.setText(exam.title);
        holder.mTvExamQuestions.setText(exam.getQuestionsNumber());
        holder.mTvDate.setText(exam_date);
    }

    @Override
    public int getItemCount() {
        return mExamsList.size();
    }

    public class ExamsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mExamCard;
        TextView mTvTitle;
        TextView mTvDate;
        TextView mTvExamQuestions;
        Button mBtnStartExam;

        public ExamsViewHolder(View view) {
            super(view);

            mExamCard = (LinearLayout) view.findViewById(R.id.examCard);
            mTvTitle = (TextView) view.findViewById(R.id.examTitle);
            mTvDate = (TextView) view.findViewById(R.id.examDate);
            mTvExamQuestions = (TextView) view.findViewById(R.id.examQuestions);
            mBtnStartExam = (Button) view.findViewById(R.id.startButton);

        }
    }

}
