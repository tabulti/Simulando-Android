package com.simulando.UI.Dashboard.Exams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simulando.API.Exam.ExamService;
import com.simulando.Components.Alternative;
import com.simulando.Interfaces.APICallback;
import com.simulando.Models.Answer;
import com.simulando.Models.Exam;
import com.simulando.Models.Question;
import com.simulando.R;
import com.simulando.Utils.Timer;

import java.util.ArrayList;

/**
 * Created by Luciano José on 16/02/2017.
 */

public class AnswerExamActivity extends AppCompatActivity {

    ExamService mExamService;
    ProgressBar mPbLoadingQuestion;
    ActionBar mToolbar;

    /**
     * Variaveis para controle do exame.
     */
    private ArrayList<Answer> mExamAnswers;
    private Exam mExam;
    private String questionNumber;
    private int questionIndex = 0;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        mExamService = ExamService.getInstance(this);

        String examId = getIntent().getStringExtra("examId");
        mExamAnswers = new ArrayList<>();

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
        mExamService.getExam(examId, new APICallback() {
            @Override
            public void onSuccess(Object response) {
                mExam = (Exam) response;
                updateQuestionInfo(mExam.questions.get(questionIndex));
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * Define qual elemento estará visivel,
     * o 'loading' ou a questão.
     *
     * @param showQuestion
     */
    public void showQuestion(boolean showQuestion) {
        if (showQuestion) {
            mPbLoadingQuestion.setVisibility(View.GONE);
            mQuestionPanel.setVisibility(View.VISIBLE);
        } else {
            mPbLoadingQuestion.setVisibility(View.VISIBLE);
            mQuestionPanel.setVisibility(View.GONE);
        }
    }

    /**
     * Atualiza as informações da Questão
     */
    public void updateQuestionInfo(Question currentQuestion) {
        showQuestion(false);

        questionNumber = getResources().getString(R.string.question_number, questionIndex, mExam.questions.size());

        mQuestionNumber.setText(questionNumber);

        mQuestionText.setText(Html.fromHtml(currentQuestion.enunciado));

        mFirstAlternative.setText(Html.fromHtml(currentQuestion.alternativa.get(0).texto).toString());
        mFirstAlternative.setApiID(currentQuestion.alternativa.get(0).id);

        mSecondAlternative.setText(Html.fromHtml(currentQuestion.alternativa.get(1).texto).toString());
        mSecondAlternative.setApiID(currentQuestion.alternativa.get(1).id);

        mThirdAlternative.setText(Html.fromHtml(currentQuestion.alternativa.get(2).texto).toString());
        mThirdAlternative.setApiID(currentQuestion.alternativa.get(2).id);

        mFourthAlternative.setText(Html.fromHtml(currentQuestion.alternativa.get(3).texto).toString());
        mFourthAlternative.setApiID(currentQuestion.alternativa.get(3).id);

        mFifthAlternative.setText(Html.fromHtml(currentQuestion.alternativa.get(4).texto).toString());
        mFifthAlternative.setApiID(currentQuestion.alternativa.get(4).id);

        showQuestion(true);
    }

    /**
     * Adiciona a resposta da questão
     * atual no array para ser cadastrado
     * ao final do simulado.
     */
    public void addAnswer(Answer answer) {
        if (questionIndex == mExam.questions.size()) {
            Log.d("TERMINOU", new Gson().toJson(mExamAnswers));
        } else {
            questionIndex++;
            mExamAnswers.add(answer);
            updateQuestionInfo(mExam.questions.get(questionIndex));
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
            Timer.stop();
            long elapsedTime = Timer.getElapsedTime();
            Timer.reset();
            Alternative selected = (Alternative) findViewById(currentAlternativeId);
            Answer questionAnswer = new Answer(selected.getApiID(), selectedAlternativeLetter, elapsedTime);
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
}
