package com.simulando.UI.Dashboard.AnswerQuestions;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.simulando.API.Answer.AnswerService;
import com.simulando.API.Questions.QuestionsService;
import com.simulando.Components.Alternative;
import com.simulando.Interfaces.APICallback;
import com.simulando.Models.Answer;
import com.simulando.Models.GenericResponse;
import com.simulando.Models.Question;
import com.simulando.R;
import com.simulando.Utils.Timer;

public class AnswerQuestionsActivity extends AppCompatActivity {


    ProgressBar mPbLoadingQuestion;
    ActionBar mToolbar;
    QuestionsService mQuestionService;
    AnswerService mAnswerService;

    /**
     * Variaveis para controle da resposta.
     */
    private Question currentQuestion;
    private int currentAlternativeId = -1;
    private String selectedAlternativeLetter = "X";

    /**
     * ELementos do Dialog
     */
    LayoutInflater mInflater;
    AlertDialog.Builder mAlertBuilder;
    View mDialogLayout;
    AlertDialog mAlertDialog;

    ImageView mIvIconFeedback;
    Button mBtnLoadQuestion;
    TextView mTvFeedback;
    TextView mTvSubjectsList;


    /**
     * Elemtnso da Questão
     */
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
            mAlertDialog.dismiss();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);

        mQuestionService = QuestionsService.getInstance(this);
        mAnswerService = AnswerService.getInstance(this);

        mToolbar = getSupportActionBar();
        mPbLoadingQuestion = (ProgressBar) findViewById(R.id.loadingQuestion);

        /**
         * Elementos para criação do
         * 'dialog' de feedback da questão.
         */
        mInflater = LayoutInflater.from(this);
        mDialogLayout = mInflater.inflate(R.layout.dialog_feedback_layout, null);
        mAlertBuilder = new AlertDialog.Builder(this);
        mAlertBuilder.setView(mDialogLayout);
        mAlertDialog = mAlertBuilder.create();

        mIvIconFeedback = (ImageView) mDialogLayout.findViewById(R.id.feedbackIcon);
        mBtnLoadQuestion = (Button) mDialogLayout.findViewById(R.id.nextQuestion);
        mBtnLoadQuestion.setOnClickListener(mBtnDialogListener);
        mTvFeedback = (TextView) mDialogLayout.findViewById(R.id.feedbackText);
        mTvSubjectsList = (TextView) mDialogLayout.findViewById(R.id.subjectsList);

        /**
         * Elementos de apresentação da questão,
         * enunciado e alternativas.
         */
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
            Timer.stop();
            long elapsedTime = Timer.getElapsedTime();
            Timer.reset();
            Alternative selected = (Alternative) findViewById(currentAlternativeId);
            Answer questionAnswer = new Answer(selected.getApiID(), selectedAlternativeLetter, elapsedTime);
            if (selectedAlternativeLetter.equals(currentQuestion.resposta)) {
                showFeedback(true, "Testando");
            } else {
                showFeedback(false, "Testando");
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
     * Apresenta uma nova questão
     * trazida da API.
     */
    public void loadQuestion() {
        showQuestion(false);

        /**
         * Reseta alternativa selecionadas
         */
        currentAlternativeId = -1;
        selectedAlternativeLetter = "X";
        mFirstAlternative.setSelected(false);
        mSecondAlternative.setSelected(false);
        mThirdAlternative.setSelected(false);
        mFourthAlternative.setSelected(false);
        mFifthAlternative.setSelected(false);

        /**
         * Busca a nova questão na API.
         */
        mQuestionService.getRandomQuestion(new APICallback() {
            @Override
            public void onSuccess(Object response) {
                Timer.start();

                currentQuestion = (Question) response;

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

            @Override
            public void onError(String message) {
                showQuestion(false);
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
     * Apresenta o dialog de feedback,
     * informado ao usuário se acertou
     * ou errou a questão.
     *
     * @param isCorrect
     * @param subjectList
     */
    public void showFeedback(boolean isCorrect, String subjectList) {
        //Ao Errar deve ser apresentado os assuntos para estudar
        mTvSubjectsList.setVisibility(View.GONE);
        if (isCorrect) {
            mTvFeedback.setText(getResources().getString(R.string.feedback_text_correct));
            mIvIconFeedback.setImageResource(R.drawable.ic_correct_answer);
        } else {
            mTvFeedback.setText(getResources().getString(R.string.feedback_text_incorrect));
            //mTvSubjectsList.setVisibility(View.VISIBLE);
            //mTvSubjectsList.setText(subjectList);
            mIvIconFeedback.setImageResource(R.drawable.ic_incorrect_answer);
        }
        mAlertDialog.show();
    }

    /**
     * Cadastra a resposta do usuário
     * para a questão atual.
     *
     * @param answer
     */
    public void answerQuestion(Answer answer) {
        mAnswerService.registerAnswer(answer, new APICallback() {
            @Override
            public void onSuccess(Object response) {
                GenericResponse resp = (GenericResponse) response;
                Log.d("REQ_STATUS", resp.status);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

}
