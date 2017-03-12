package com.simulando.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simulando.Models.Exam;
import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.AnswerExam.AnswerExamActivity;
import com.simulando.UI.Dashboard.Exams.ExamResult.ExamResultActivity;
import com.simulando.Utils.DateUtils;

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

    public void startExam(String examId) {
        Intent answerExam = new Intent(mContext, AnswerExamActivity.class);
        answerExam.putExtra("examId", examId);
        mContext.startActivity(answerExam);
    }

    public void showResult(String examId) {
        Intent examResult = new Intent(mContext, ExamResultActivity.class);
        examResult.putExtra("examId", examId);
        mContext.startActivity(examResult);
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
        boolean isAvailable = DateUtils.isExamAvailable(exam.startDate, exam.endDate);

        if (exam.cancelled) {
            holder.mVLineColor.setBackgroundColor(mContext.getResources().getColor(R.color.red));
            holder.mBtnActionExam.setVisibility(View.GONE);
            holder.mTvInfo.setText(R.string.exam_cancelled);
        } else if (!isAvailable || exam.done) {
            holder.mVLineColor.setBackgroundColor(mContext.getResources().getColor(R.color.red));
            holder.mBtnActionExam.setVisibility(View.VISIBLE);
            holder.mBtnActionExam.setText(R.string.exam_result_button);
            holder.mTvInfo.setText(R.string.exam_done);
        } else {
            String examDate = mContext.getResources().getString(R.string.available_date,
                    DateUtils.getExamDate(exam.startDate, exam.endDate));

            holder.mVLineColor.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.mBtnActionExam.setText(R.string.start_exam_button);
            holder.mBtnActionExam.setVisibility(View.VISIBLE);
            holder.mTvInfo.setText(examDate);
        }


        holder.mBtnActionExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exam.done) {
                    showResult(exam.id);
                } else if (!exam.done && !exam.cancelled) {
                    startExam(exam.id);
                }
            }
        });

        holder.mTvTitle.setText(exam.title);
        holder.mTvExamQuestions.setText(exam.getQuestionsNumber());
    }

    @Override
    public int getItemCount() {
        return mExamsList.size();
    }

    public class ExamsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mExamCard;
        View mVLineColor;
        TextView mTvTitle;
        TextView mTvInfo;
        TextView mTvExamQuestions;
        Button mBtnActionExam;

        public ExamsViewHolder(View view) {
            super(view);

            mExamCard = (LinearLayout) view.findViewById(R.id.examCard);
            mVLineColor = view.findViewById(R.id.lineColor);
            mTvTitle = (TextView) view.findViewById(R.id.examTitle);
            mTvInfo = (TextView) view.findViewById(R.id.examInfo);
            mTvExamQuestions = (TextView) view.findViewById(R.id.examQuestions);
            mBtnActionExam = (Button) view.findViewById(R.id.startButton);
        }
    }

}
