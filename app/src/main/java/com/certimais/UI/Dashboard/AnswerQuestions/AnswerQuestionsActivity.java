package com.certimais.UI.Dashboard.AnswerQuestions;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.certimais.Components.Alternative;
import com.certimais.R;

public class AnswerQuestionsActivity extends AppCompatActivity {

    private int currentAlternative = -1;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);

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

    }


    public void selectAlternative(int alternativeId) {

        if (currentAlternative == alternativeId) {
            loadNextQuestion();
            return;
        }

        switch (alternativeId) {
            case R.id.firstAlternative:
                mFirstAlternative.setSelected(true);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);
                break;
            case R.id.secondAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(true);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);
                break;
            case R.id.thirdAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(true);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(false);
                break;
            case R.id.fourthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(true);
                mFifthAlternative.setSelected(false);
                break;
            case R.id.fifthAlternative:
                mFirstAlternative.setSelected(false);
                mSecondAlternative.setSelected(false);
                mThirdAlternative.setSelected(false);
                mFourthAlternative.setSelected(false);
                mFifthAlternative.setSelected(true);
                break;

        }

        currentAlternative = alternativeId;

    }

    public void loadNextQuestion() {
        currentAlternative = -1;

        mFirstAlternative.setSelected(false);
        mSecondAlternative.setSelected(false);
        mThirdAlternative.setSelected(false);
        mFourthAlternative.setSelected(false);
        mFifthAlternative.setSelected(false);

        //Mudar textos das alternativas e do enunciado
    }

}
