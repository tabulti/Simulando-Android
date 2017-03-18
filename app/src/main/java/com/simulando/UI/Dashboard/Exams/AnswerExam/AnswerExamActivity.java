package com.simulando.UI.Dashboard.Exams.AnswerExam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simulando.API.Exam.ExamService;
import com.simulando.Components.Alternative;
import com.simulando.Interfaces.Callback;
import com.simulando.Models.Answer;
import com.simulando.Models.Exam;
import com.simulando.Models.ExamAnswer;
import com.simulando.Models.Question;
import com.simulando.R;
import com.simulando.UI.Dashboard.Exams.ExamResult.ExamResultActivity;
import com.simulando.Utils.AppUtils;
import com.simulando.Utils.CommonUtils;
import com.simulando.Utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

import static android.view.View.GONE;

/**
 * Created by Luciano José on 16/02/2017.
 */

public class AnswerExamActivity extends AppCompatActivity {

    ExamService mExamService;
    ProgressBar mPbLoadingQuestion;
    AlertDialog mCancelDialog;
    AlertDialog.Builder mBuilder;
    ActionBar mToolbar;


    /**
     * Variaveis para controle do exame.
     */
    private Chronometer mChronometer;
    private CountDownTimer mCounter;
    public long remainingTime;
    private boolean isCounterRunning;
    private ExamAnswer mExamAnswer;
    private ArrayList<Answer> mAnswers;
    private Exam mExam;
    private String questionNumber;
    private int questionIndex = 1;
    private int currentAlternativeId = -1;
    private String selectedAlternativeLetter = "X";

    /**
     * Elementos da Questão
     */
    RelativeLayout mExamQuestionBox;
    ScrollView mQuestionPanel;
    TextView mQuestionText;
    TextView mQuestionNumber;
    Alternative mFirstAlternative;
    Alternative mSecondAlternative;
    Alternative mThirdAlternative;
    Alternative mFourthAlternative;
    Alternative mFifthAlternative;

    View.OnClickListener mAlternativeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int alternativeId = v.getId();
            selectAlternative(alternativeId);
        }
    };

    DialogInterface.OnClickListener mCancelListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            cancelExam();
        }
    };


    DialogInterface.OnClickListener mContinueListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        mExamService = ExamService.getInstance(this);
        mExamAnswer = new ExamAnswer();

        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.cancel_dialog_title)
                .setMessage(R.string.cancel_dialog_text);
        mBuilder.setPositiveButton(R.string.cancel_dialog_continue, mContinueListener);
        mBuilder.setNegativeButton(R.string.cancel_dialog_cancel, mCancelListener);
        mCancelDialog = mBuilder.create();


        String examId = getIntent().getStringExtra("examId");
        mExamAnswer.id = examId;
        remainingTime = 0;
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mAnswers = new ArrayList<>();

        mToolbar = getSupportActionBar();
        mPbLoadingQuestion = (ProgressBar) findViewById(R.id.loadingQuestion);

        /**
         * Elementos de apresentação da questão,
         * enunciado e alternativas.
         */
        mExamQuestionBox = (RelativeLayout) findViewById(R.id.examQuestionBox);
        mExamQuestionBox.setVisibility(View.VISIBLE);

        mQuestionPanel = (ScrollView) findViewById(R.id.questionPanel);
        mQuestionText = (TextView) findViewById(R.id.examQuestionText);
        TextView txt = (TextView) findViewById(R.id.questionText);
        txt.setVisibility(View.GONE);
        mQuestionNumber = (TextView) findViewById(R.id.examQuestionNumber);

        mFirstAlternative = (Alternative) findViewById(R.id.firstAlternative);
        mSecondAlternative = (Alternative) findViewById(R.id.secondAlternative);
        mThirdAlternative = (Alternative) findViewById(R.id.thirdAlternative);
        mFourthAlternative = (Alternative) findViewById(R.id.fourthAlternative);
        mFifthAlternative = (Alternative) findViewById(R.id.fifthAlternative);

        mFirstAlternative.setOnClickListener(mAlternativeListener);
        mSecondAlternative.setOnClickListener(mAlternativeListener);
        mThirdAlternative.setOnClickListener(mAlternativeListener);
        mFourthAlternative.setOnClickListener(mAlternativeListener);
        mFifthAlternative.setOnClickListener(mAlternativeListener);

        getExamInfo(examId);
    }

    /**
     * Busca as informações do simulado
     * para inicia-lo.
     *
     * @param examId
     */
    public void getExamInfo(String examId) {
        showQuestion(false);
        mExamService.getExam(examId, new Callback() {
            @Override
            public void onSuccess(Object response) {
                mExam = (Exam) response;
                startCounter(mExam.estimatedTime * 60000);
                updateQuestionInfo(mExam.questions.get(questionIndex));
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * Define qual elemento estará visivel,
     * o "loading" ou a questão.
     *
     * @param showQuestion
     */
    public void showQuestion(boolean showQuestion) {
        if (showQuestion) {
            mPbLoadingQuestion.setVisibility(GONE);
            mQuestionPanel.setVisibility(View.VISIBLE);
        } else {
            mPbLoadingQuestion.setVisibility(View.VISIBLE);
            mQuestionPanel.setVisibility(GONE);
        }
    }

    /**
     * Atualiza as informações da Questão
     */
    public void updateQuestionInfo(Question currentQuestion) {
        showQuestion(false);

        questionNumber = getResources().getString(R.string.question_number, questionIndex, mExam.questions.size());

        mQuestionNumber.setText(questionNumber);

        mQuestionText.setText(Html.fromHtml(currentQuestion.statement));

        mFirstAlternative.setText(Html.fromHtml(currentQuestion.alternatives.get(0).text).toString());
        mFirstAlternative.setApiID(currentQuestion.alternatives.get(0).id);

        mSecondAlternative.setText(Html.fromHtml(currentQuestion.alternatives.get(1).text).toString());
        mSecondAlternative.setApiID(currentQuestion.alternatives.get(1).id);

        mThirdAlternative.setText(Html.fromHtml(currentQuestion.alternatives.get(2).text).toString());
        mThirdAlternative.setApiID(currentQuestion.alternatives.get(2).id);

        mFourthAlternative.setText(Html.fromHtml(currentQuestion.alternatives.get(3).text).toString());
        mFourthAlternative.setApiID(currentQuestion.alternatives.get(3).id);

        mFifthAlternative.setText(Html.fromHtml(currentQuestion.alternatives.get(4).text).toString());
        mFifthAlternative.setApiID(currentQuestion.alternatives.get(4).id);

        showQuestion(true);
        startChronometer();
    }

    /**
     * Adiciona a resposta da questão
     * atual no array para ser cadastrado
     * ao final do simulado.
     */
    public void addAnswer(Answer answer) {

        mAnswers.add(answer);

        if (questionIndex == mExam.questions.size()) {
            stopCounter();

            long elapsedTime = ((mExam.estimatedTime * 60000) - remainingTime);
            Log.d("elapsed", elapsedTime + "");
            mExamAnswer.answerDate = new Date();
            mExamAnswer.elapsedTime = elapsedTime;
            mExamAnswer.answers.addAll(mAnswers);
            registerAnswers();
        } else {
            questionIndex++;
            resetOptions();
            updateQuestionInfo(mExam.questions.get(questionIndex - 1));
        }
    }

    /**
     * Seleciona a alternativa
     * e verifica se a resposta está
     * correta ou não.
     *
     * @param alternativeId
     */
    public void selectAlternative(int alternativeId) {
        if (currentAlternativeId == alternativeId) {
            stopChronometer();
            long elapsedTime = getElapsedTime();
            Alternative selected = (Alternative) findViewById(currentAlternativeId);
            Answer questionAnswer = new Answer(selected.getApiID(), selectedAlternativeLetter, elapsedTime, new Date());
            addAnswer(questionAnswer);
            return;
        }

        switch (alternativeId) {
            case R.id.firstAlternative:
                mFirstAlternative.setSelected(true);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = "A";

                break;
            case R.id.secondAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(true);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = "B";

                break;
            case R.id.thirdAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(true);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = "C";

                break;
            case R.id.fourthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(true);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = "D";

                break;
            case R.id.fifthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(true);

                selectedAlternativeLetter = "E";

                break;

        }

        currentAlternativeId = alternativeId;
    }

    /**
     * Reseta alternativa selecionada
     */
    public void resetOptions() {
        currentAlternativeId = -1;
        selectedAlternativeLetter = "X";
        mFirstAlternative.setSelected(false);
        mSecondAlternative.setSelected(false);
        mThirdAlternative.setSelected(false);
        mFourthAlternative.setSelected(false);
        mFifthAlternative.setSelected(false);
    }

    /**
     * Cadastra as respostas do simulado.
     */
    public void registerAnswers() {
        CommonUtils.showLoadingDialog(this);
        mExamService.registerAnswer(mExamAnswer, new Callback() {
            @Override
            public void onSuccess(Object response) {
                showResult();
                CommonUtils.hideDialog();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * Apresenta o resultado após
     * finalizar o simulado
     */
    public void showResult() {
        Intent examResult = new Intent(this, ExamResultActivity.class);
        examResult.putExtra("examId", mExam.id);
        startActivity(examResult);
    }

    /**
     * Iniciar o contador de
     * tempo do simulado
     *
     * @param time
     */
    public void startCounter(long time) {
        isCounterRunning = true;

        if (!isCounterRunning) {
            mCounter.cancel();
        }

        mCounter = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                if (!isCounterRunning) {
                    mCounter.cancel();
                } else {
                    updateTitleInfo(mExam.title, DateUtils.formatMinSec(millisUntilFinished));
                }
            }

            @Override
            public void onFinish() {
            }

        };
        mCounter.start();
    }

    /**
     * Para o contador de tempo
     * do simulado.
     */
    public void stopCounter() {
        isCounterRunning = false;
    }

    /**
     * Atualiza o titulo
     * e subtitulo do actionbar
     */
    public void updateTitleInfo(String title, String time) {
        String subtitle = "Tempo restante: ( " + time + " )";
        SpannableString spTitle = new SpannableString(title);
        SpannableString spSubtitle = new SpannableString(subtitle);

        spTitle.setSpan(new RelativeSizeSpan(0.9f), 0, title.length(), 0);
        spSubtitle.setSpan(new RelativeSizeSpan(0.9f), 0, subtitle.length(), 0);
        spSubtitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0, subtitle.length(), 0);

        mToolbar.setTitle(spTitle);
        mToolbar.setSubtitle(spSubtitle);
    }

    /**
     * Inicia o cronometro para
     * contar o tempo do simulado
     */
    public void startChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }


    /**
     * Para o cronometro do simulado.
     */
    public void stopChronometer() {
        mChronometer.stop();
    }

    /**
     * Verifica o tempo gasto para
     * realizar o simulado
     *
     * @return
     */
    public long getElapsedTime() {
        long elapsedTime = (SystemClock.elapsedRealtime() - mChronometer.getBase());
        return elapsedTime;
    }

    /**
     * Cancela o simulado atual
     */
    public void cancelExam() {
        CommonUtils.showLoadingDialog(this);
        mExamService.cancelExam(mExam.id, new Callback() {
            @Override
            public void onSuccess(Object response) {
                CommonUtils.hideDialog();
                AppUtils.goToDashboard(AnswerExamActivity.this);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        mCancelDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return true;
    }

}
