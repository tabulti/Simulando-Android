package com.simulando.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simulando.Models.AreaStatistic;
import com.simulando.Models.Exam;
import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.AnswerExam.AnswerExamActivity;
import com.simulando.Utils.DateUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class ExamGradeAdapter extends RecyclerView.Adapter<ExamGradeAdapter.ExamGradeViewHolder> {
    int[] colors = {R.color.bondi_blue, R.color.cornflower_blue,
            R.color.crusta, R.color.shamrock, R.color.sea_buck};
    private ArrayList<AreaStatistic> mExamGradeList;
    private Context mContext;

    public ExamGradeAdapter(Context context, ArrayList<AreaStatistic> examGradeList) {
        this.mExamGradeList = examGradeList;
        this.mContext = context;
    }

    public void updateList(ArrayList<AreaStatistic> examGradeList) {
        this.mExamGradeList.clear();
        this.mExamGradeList.addAll(examGradeList);
    }


    @Override
    public ExamGradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exam_grade_layout, parent, false);

        ExamGradeViewHolder holder = new ExamGradeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ExamGradeViewHolder holder, int position) {

        AreaStatistic area = mExamGradeList.get(position);
        int color = mContext.getResources().getColor(colors[position]);

        holder.mVLineColor.setBackgroundColor(color);
        holder.mTvAreaName.setTextColor(color);
        holder.mTvAreaGrade.setTextColor(color);

        holder.mTvAreaName.setText(area.name);
        holder.mTvAreaGrade.setText(String.valueOf(area.grade));
        holder.mTvCorrectAnswers.setText(mContext.getResources().getString(R.string.total_correct_questions, area.correctAnswers));
        holder.mTvWrongAnswers.setText(mContext.getResources().getString(R.string.total_wrong_questions, area.wrongAnswers));

    }

    @Override
    public int getItemCount() {
        return mExamGradeList.size();
    }

    public class ExamGradeViewHolder extends RecyclerView.ViewHolder {

        View mVLineColor;
        TextView mTvAreaName;
        TextView mTvAreaGrade;
        TextView mTvCorrectAnswers;
        TextView mTvWrongAnswers;

        public ExamGradeViewHolder(View view) {
            super(view);

            mVLineColor = view.findViewById(R.id.lineColor);
            mTvAreaName = (TextView) view.findViewById(R.id.areaName);
            mTvAreaGrade = (TextView) view.findViewById(R.id.areaGrade);
            mTvCorrectAnswers = (TextView) view.findViewById(R.id.correctAnswers);
            mTvWrongAnswers = (TextView) view.findViewById(R.id.wrongAnswers);
        }
    }

}
