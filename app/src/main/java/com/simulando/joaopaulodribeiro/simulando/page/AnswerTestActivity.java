package com.simulando.joaopaulodribeiro.simulando.page;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.simulando.joaopaulodribeiro.simulando.Callbacks;
import com.simulando.joaopaulodribeiro.simulando.MainActivity;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.databinding.ActivityAnswerTestBinding;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.AnswerTest;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.QuestionAppModel;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.adapters.AnswerTestAdapter;
import com.simulando.joaopaulodribeiro.simulando.utils.MyCountDownTimer;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestActivity extends MainActivity implements Serializable, Callbacks.OnQuestionAnsweredListener {

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private MyCountDownTimer timer;
    private TextView mTimerTv;
    private long mTimeLeftOnCountDown;
    private Test mTest;
    private List<AnswerTest> answerTestBody;
    private List<QuestionAppModel> questionsAnsweredFragments;
    private QuestionAppModel questionAppModel;
    private RelativeLayout mSendAnswerLayout;
    private RelativeLayout mAnswerTestContainerLayout;

    private void bindViews() {
        ActivityAnswerTestBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_answer_test);

        mToolbar = binding.toolbarAnswerTest;
        mViewPager = binding.answerTestViewPager;
        mTimerTv = binding.answerTestTimer;

        mSendAnswerLayout = binding.sendAnswerLayout;

        mAnswerTestContainerLayout = binding.answerTestContainerLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();

        mTest = (Test) getIntent().getSerializableExtra("Test");

        if (answerTestBody == null && questionsAnsweredFragments == null) {
            answerTestBody = new ArrayList<>();
            questionsAnsweredFragments = new ArrayList<>();
        }

        if (mTest != null) {
            AnswerTestAdapter adapter = new AnswerTestAdapter(getSupportFragmentManager(), mTest, this);

            mViewPager.setClipToPadding(false);

            mViewPager.setPageMargin(48);

            mViewPager.setAdapter(adapter);
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Utils.getSavedTimeInMilliseconds(this, Utils.CALENDAR_TIME_SAVED) > 0L) {

            mTimeLeftOnCountDown = Utils.getSavedTimeInMilliseconds(this, Utils.COUNTDOWN_TIME_SAVED);

            long difference = Calendar.getInstance().getTimeInMillis() -
                    Utils.getSavedTimeInMilliseconds(this, Utils.CALENDAR_TIME_SAVED);

            difference = (mTimeLeftOnCountDown - difference);

            setTimer(mTimerTv, difference);
            timer.start();

        } else {
            if (mTest != null) {
                Long estimatedTime = Utils.minutesToMilliseconds(Integer.valueOf(mTest.getEstimated_time()));
                setTimer(mTimerTv, estimatedTime);
                timer.start();
            }
        }
    }

    private void setTimer(TextView mTimerTv, long millisInFuture) {
        timer = new MyCountDownTimer(AnswerTestActivity.this, mTimerTv, millisInFuture, 1000L,
                getOnMillisUntilFinishedListener(), getTimerCountDownFinishedListener());
    }

    @NonNull
    private Callbacks.OnTimerCountDownFinishedListener getTimerCountDownFinishedListener() {
        return new Callbacks.OnTimerCountDownFinishedListener() {
            @Override
            public void onTimerCountDownFinished() {

                AlertDialog.Builder builder = new AlertDialog.Builder(AnswerTestActivity.this);
                builder.setTitle(R.string.alert_count_down_finished_title);
                builder.setMessage(R.string.alert_count_down_finished_msg);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnswerTestActivity.this.finish();
                    }
                });

                builder.show();
            }
        };
    }

    @NonNull
    private Callbacks.OnMillisUntilFinishedListener getOnMillisUntilFinishedListener() {
        return new Callbacks.OnMillisUntilFinishedListener() {
            @Override
            public void onMillisUntilFinished(long millisUntilFinished) {
                mTimeLeftOnCountDown = millisUntilFinished;
                if (mTimeLeftOnCountDown <= 0) {
                    timer.cancel();
                }
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.saveTimeInMilliseconds(this, Calendar.getInstance().getTimeInMillis(), Utils.CALENDAR_TIME_SAVED);
        Utils.saveTimeInMilliseconds(this, mTimeLeftOnCountDown, Utils.COUNTDOWN_TIME_SAVED);

        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            Utils.saveTimeInMilliseconds(this, 0L, Utils.CALENDAR_TIME_SAVED);
            Utils.saveTimeInMilliseconds(this, 0L, Utils.COUNTDOWN_TIME_SAVED);
            timer.cancel();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_answer_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.close_test_alert_title);
        builder.setMessage(R.string.close_test_alert_msg);
        builder.setPositiveButton(R.string.close_test_alert_positive_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AnswerTestActivity.this.finish();
            }
        });
        builder.setNegativeButton(R.string.close_test_alert_negative_button, null);

        builder.show();
    }

    public AnswerTest isQuestionYetAnswered(int fragmentPosition) {
        for (QuestionAppModel questionModel : questionsAnsweredFragments) {
            if (questionModel.getQuestionFragmentOrder() == fragmentPosition) {

                return questionModel.getQuestion();
            }
        }
        return null;
    }

    @Override
    public void OnQuestionAnswered(AnswerTest answerTest, int fragmentPosition) {
        if (answerTest != null) {
            if (questionsAnsweredFragments != null) {

                for (QuestionAppModel questionModel : questionsAnsweredFragments) {
                    if (questionModel.getQuestionFragmentOrder() == fragmentPosition) {

                        long newElapsedTime = answerTest.getElapsed_time() + questionModel.getQuestion().getElapsed_time();
                        questionModel.getQuestion().setElapsed_time(newElapsedTime);
                        questionModel.getQuestion().setAlternative_id(answerTest.getAlternative_id());
                        questionModel.getQuestion().setTest_question_id(answerTest.getTest_question_id());
                        //TODO: set answer date

                        questionsAnsweredFragments.set(fragmentPosition, questionModel);

                        return;
                    }
                }
            }

            questionAppModel = new QuestionAppModel(fragmentPosition, answerTest);
            questionsAnsweredFragments.add(questionAppModel);
        }
    }

    public void showSendLayout(int totalQuestions) {
        if (questionsAnsweredFragments.size() < totalQuestions) {
            mSendAnswerLayout.setVisibility(View.GONE);
        } else {
            YoYo.with(Techniques.SlideInUp)
                    .duration(700)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mSendAnswerLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mAnswerTestContainerLayout.setPadding(0, 0, 0, Utils
                                    .dpToPixel(AnswerTestActivity.this, 56));
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(mSendAnswerLayout);
        }
    }
}
