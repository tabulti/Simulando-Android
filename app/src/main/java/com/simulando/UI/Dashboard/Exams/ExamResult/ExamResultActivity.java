package com.simulando.UI.Dashboard.Exams.ExamResult;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.simulando.API.Exam.ExamService;
import com.simulando.Adapters.ExamGradeAdapter;
import com.simulando.Interfaces.Callback;
import com.simulando.Models.ExamStatistic;
import com.simulando.R;
import com.simulando.Utils.AppUtils;

public class ExamResultActivity extends AppCompatActivity {

    ActionBar mToolbar;

    ExamService mExamService;
    ExamStatistic examStatistic;
    RecyclerView mRecyclerView;
    ExamGradeAdapter mAdapter;

    TextView mTvQuestionsNumber;
    TextView mTvCorrectAnswers;
    TextView mTvWrongAnswers;
    TextView mTvAverage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

        mExamService = ExamService.getInstance(this);

        examStatistic = new ExamStatistic();
        String examId = getIntent().getStringExtra("examId");
        getResult(examId);

        mToolbar = getSupportActionBar();

        mTvQuestionsNumber = (TextView) findViewById(R.id.questionsNumber);
        mTvCorrectAnswers = (TextView) findViewById(R.id.correctAnswers);
        mTvWrongAnswers = (TextView) findViewById(R.id.wrongAnswers);
        mTvAverage = (TextView) findViewById(R.id.averageGrade);


        mRecyclerView = (RecyclerView) findViewById(R.id.examGradeList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mAdapter = new ExamGradeAdapter(this, examStatistic.statistic);

        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * Busca as informações do
     * desempenho do aluno no simulado.
     *
     * @param examId
     */
    public void getResult(String examId) {
        mExamService.getExamResult(examId, new Callback() {
            @Override
            public void onSuccess(Object response) {
                examStatistic = (ExamStatistic) response;
                updateInfo();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * Atualiza as informações
     * do resultado
     */
    public void updateInfo() {
        mToolbar.setTitle(examStatistic.examTitle);
        mAdapter.updateList(examStatistic.statistic);
        mAdapter.notifyDataSetChanged();

        mTvQuestionsNumber.setText(getResources().getString(R.string.total_questions, examStatistic.questionsNumber));
        mTvCorrectAnswers.setText(getResources().getString(R.string.total_correct_questions, examStatistic.totalCorrectAnswers));
        mTvWrongAnswers.setText(getResources().getString(R.string.total_wrong_questions, examStatistic.totalWrongAnswers));
        mTvAverage.setText(getResources().getString(R.string.average_grade, examStatistic.average));
    }

    @Override
    public void onBackPressed() {
        AppUtils.goToDashboard(this);
    }
}
