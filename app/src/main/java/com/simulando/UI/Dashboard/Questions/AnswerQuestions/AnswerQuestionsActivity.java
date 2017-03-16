package com.simulando.UI.Dashboard.Questions.AnswerQuestions;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simulando.API.Answer.AnswerService;
import com.simulando.API.Questions.QuestionsService;
import com.simulando.Components.Alternative;
import com.simulando.Consts.AppConsts;
import com.simulando.Interfaces.Callback;
import com.simulando.Models.Answer;
import com.simulando.Models.GenericResponse;
import com.simulando.Models.Question;
import com.simulando.R;
import com.simulando.UI.Dashboard.Questions.QuestionFeedback.FeedbackDialogFragment;

import java.util.Date;

public class AnswerQuestionsActivity extends AppCompatActivity implements FeedbackDialogFragment.FinishDialogListener {


    ProgressBar mPbLoadingQuestion;
    ActionBar mToolbar;
    QuestionsService mQuestionService;
    AnswerService mAnswerService;
    FragmentManager mFragmentManager;

    /**
     * Variaveis para controle da resposta.
     */
    private Chronometer mChronometer;
    private Question currentQuestion;
    private int currentAlternativeId = -1;
    private char selectedAlternativeLetter = 'X';

    /**
     * ELementos do Dialog
     */
    FeedbackDialogFragment mFeedbackDialogFragment;


    /**
     * Elementos da Questão
     */
    RelativeLayout mExamQuestionBox;
    ScrollView mQuestionPanel;
    TextView mQuestionText;
    Alternative mFirstAlternative;
    Alternative mSecondAlternative;
    Alternative mThirdAlternative;
    Alternative mFourthAlternative;
    Alternative mFifthAlternative;

    View.OnClickListener mBtnDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadQuestion();
        }
    };


    View.OnClickListener mAlternativeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int alternativeId = v.getId();
            selectAlternative(alternativeId);
        }
    };

    Chronometer.OnChronometerTickListener chronometerTickListener = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            String title = getResources().getString(R.string.enem_label) + " " + currentQuestion.year;
            updateTitleInfo(title, currentQuestion.subject.name);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        mQuestionService = QuestionsService.getInstance(this);
        mAnswerService = AnswerService.getInstance(this);

        mToolbar = getSupportActionBar();
        mPbLoadingQuestion = (ProgressBar) findViewById(R.id.loadingQuestion);

        /**
         * Elementos para criação do
         * 'dialog' de feedback da questão.
         */
        mFragmentManager = getSupportFragmentManager();
        mFeedbackDialogFragment = FeedbackDialogFragment.newInstance();
        mFeedbackDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.FeedbackDialog);


        /**
         * Elementos de apresentação da questão,
         * enunciado e alternativess.
         */
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setOnChronometerTickListener(chronometerTickListener);

        mExamQuestionBox = (RelativeLayout) findViewById(R.id.examQuestionBox);
        mExamQuestionBox.setVisibility(View.GONE);

        mQuestionPanel = (ScrollView) findViewById(R.id.questionPanel);
        mQuestionText = (TextView) findViewById(R.id.questionText);

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

        /**
         * Carrega a primeira questão
         */
        loadQuestion();

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
            if (selectedAlternativeLetter == currentQuestion.answer) {
                showFeedback(true, currentQuestion.score);
            } else {
                showFeedback(false, currentQuestion.score);
            }
            answerQuestion(questionAnswer);
            return;
        }

        switch (alternativeId) {
            case R.id.firstAlternative:
                mFirstAlternative.setSelected(true);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = 'A';

                break;
            case R.id.secondAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(true);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = 'B';

                break;
            case R.id.thirdAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(true);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = 'C';

                break;
            case R.id.fourthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(true);
                mFifthAlternative.setSelected(false);

                selectedAlternativeLetter = 'D';

                break;
            case R.id.fifthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(true);

                selectedAlternativeLetter = 'E';

                break;

        }

        currentAlternativeId = alternativeId;

    }

    /**
     * Apresenta uma nova questão
     * trazida do BD.
     */
    public void loadQuestion() {
        showQuestion(false);
        resetOptions();

        /**
         * Busca a nova questão no BD.
         */
        mQuestionService.getRandomQuestion(new Callback() {
            @Override
            public void onSuccess(Object response) {
                currentQuestion = (Question) response;
                updateQuestionInfo();
            }

            @Override
            public void onError(String message) {
                showQuestion(false);
            }
        });
    }

    /**
     * Atualiza as informações
     * da questão
     */
    public void updateQuestionInfo() {
        String title = getResources().getString(R.string.enem_label) + " " + currentQuestion.year;
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

        updateTitleInfo(title, currentQuestion.subject.name);
        showQuestion(true);
        startChronometer();
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
     * Apresenta o dialog de feedback,
     * informado ao usuário se acertou
     * ou errou a questão.
     *
     * @param isCorrect
     * @param points
     */
    public void showFeedback(boolean isCorrect, int points) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mFeedbackDialogFragment.setDialogInfo(isCorrect, points);
        mFeedbackDialogFragment.show(mFragmentManager, "DialogFeedback");
    }

    /**
     * Cadastra a resposta do usuário
     * para a questão atual.
     *
     * @param answer
     */
    public void answerQuestion(Answer answer) {
        mAnswerService.registerAnswer(answer, new Callback() {
            @Override
            public void onSuccess(Object response) {
                GenericResponse resp = (GenericResponse) response;
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    @Override
    public void onFinishDialog(int action) {

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        if (action == AppConsts.LOAD_QUESTION_ACTION) {
            loadQuestion();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Reseta alternativa selecionada
     */
    public void resetOptions() {
        currentAlternativeId = -1;
        selectedAlternativeLetter = 'X';
        mFirstAlternative.setSelected(false);
        mSecondAlternative.setSelected(false);
        mThirdAlternative.setSelected(false);
        mFourthAlternative.setSelected(false);
        mFifthAlternative.setSelected(false);
    }


    /**
     * Atualiza o titulo
     * e subtitulo do actionbar
     */
    public void updateTitleInfo(String title, String subtitle) {
        title += " ( " + mChronometer.getText().toString() + " )";
        SpannableString spTitle = new SpannableString(title);
        SpannableString spSubtitle = new SpannableString(subtitle);

        spTitle.setSpan(new RelativeSizeSpan(0.9f), 0, title.length(), 0);
        spSubtitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0, subtitle.length(), 0);
        spSubtitle.setSpan(new RelativeSizeSpan(0.9f), 0, subtitle.length(), 0);

        mToolbar.setTitle(spTitle);
        mToolbar.setSubtitle(spSubtitle);
    }

    /**
     * Iniciar o contador de tempo
     * para cada questão.
     */
    public void startChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    /**
     * Pausa o contador de tempo
     * da questão.
     */
    public void stopChronometer() {
        mChronometer.stop();
    }

    /**
     * Verifica o tempo gasto
     * para responder a questão atual
     */
    public long getElapsedTime() {
        long elapsedTime = (SystemClock.elapsedRealtime() - mChronometer.getBase());
        return elapsedTime;
    }

}
