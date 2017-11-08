package com.simulando.joaopaulodribeiro.simulando.page.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simulando.joaopaulodribeiro.simulando.Callbacks;
import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.AnswerTest;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Question;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;
import com.simulando.joaopaulodribeiro.simulando.page.AnswerTestActivity;

import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 01/10/2017.
 */

public class AnswerTestFragment extends Fragment implements Callbacks.OnMillisUntilFinishedListener {
    private int fragmentPosition;
    private Test mTest;
    private TextView questionCounterTv;
    private TextView questionMsgTv;
    private TextView questionReferenceTv;
    private RelativeLayout alternativeA;
    private RelativeLayout alternativeB;
    private RelativeLayout alternativeC;
    private RelativeLayout alternativeD;
    private RelativeLayout alternativeE;
    private TextView alternativeATxt;
    private TextView alternativeBTxt;
    private TextView alternativeCTxt;
    private TextView alternativeDTxt;
    private TextView alternativeETxt;
    private TextView alternativeALetter;
    private TextView alternativeBLetter;
    private TextView alternativeCLetter;
    private TextView alternativeDLetter;
    private TextView alternativeELetter;
    private LinearLayout alternativeALL;
    private LinearLayout alternativeBLL;
    private LinearLayout alternativeCLL;
    private LinearLayout alternativeDLL;
    private LinearLayout alternativeELL;
    private RelativeLayout mLastChoice;
    private AnswerTest mAnswerTest;

    private long timeRemmainingOnEnterFragment;
    private long timeRemmaining;

    private List<AnswerTest> mAnswers;

    private Callbacks.OnQuestionAnsweredListener mQuestionAnsweredListener;

    private void bindViews(View view) {
        questionCounterTv = (TextView) view.findViewById(R.id.simulate_cv_end_date_tv);
        questionMsgTv = (TextView) view.findViewById(R.id.question_msg_tv);
        questionReferenceTv = (TextView) view.findViewById(R.id.question_reference_tv);
        alternativeA = (RelativeLayout) view.findViewById(R.id.alternative_a);
        alternativeB = (RelativeLayout) view.findViewById(R.id.alternative_b);
        alternativeC = (RelativeLayout) view.findViewById(R.id.alternative_c);
        alternativeD = (RelativeLayout) view.findViewById(R.id.alternative_d);
        alternativeE = (RelativeLayout) view.findViewById(R.id.alternative_e);
        alternativeALetter = (TextView) alternativeA.findViewById(R.id.alternativeLetter);
        alternativeBLetter = (TextView) alternativeB.findViewById(R.id.alternativeLetter);
        alternativeCLetter = (TextView) alternativeC.findViewById(R.id.alternativeLetter);
        alternativeDLetter = (TextView) alternativeD.findViewById(R.id.alternativeLetter);
        alternativeELetter = (TextView) alternativeE.findViewById(R.id.alternativeLetter);
        alternativeATxt = (TextView) view.findViewById(R.id.alternative_a_txt);
        alternativeBTxt = (TextView) view.findViewById(R.id.alternative_b_txt);
        alternativeCTxt = (TextView) view.findViewById(R.id.alternative_c_txt);
        alternativeDTxt = (TextView) view.findViewById(R.id.alternative_d_txt);
        alternativeETxt = (TextView) view.findViewById(R.id.alternative_e_txt);
        alternativeALL = (LinearLayout) view.findViewById(R.id.alternative_a_ll);
        alternativeBLL = (LinearLayout) view.findViewById(R.id.alternative_b_ll);
        alternativeCLL = (LinearLayout) view.findViewById(R.id.alternative_c_ll);
        alternativeDLL = (LinearLayout) view.findViewById(R.id.alternative_d_ll);
        alternativeELL = (LinearLayout) view.findViewById(R.id.alternative_e_ll);


        alternativeALL.setOnClickListener(getOnAlternativeClickListener(R.id.alternative_a));
        alternativeBLL.setOnClickListener(getOnAlternativeClickListener(R.id.alternative_b));
        alternativeCLL.setOnClickListener(getOnAlternativeClickListener(R.id.alternative_c));
        alternativeDLL.setOnClickListener(getOnAlternativeClickListener(R.id.alternative_d));
        alternativeELL.setOnClickListener(getOnAlternativeClickListener(R.id.alternative_e));
    }

    @NonNull
    private View.OnClickListener getOnAlternativeClickListener(final int viewId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout backGround = (RelativeLayout) v.findViewById(viewId);
                backGround.setBackgroundResource(R.drawable.alternative_selected_background_shape);
                setOptUnselected(backGround);

                setAnswerParams();
            }
        };
    }

    public void setOptUnselected(RelativeLayout lastChoice) {
        if (mLastChoice == null) {
            mLastChoice = lastChoice;
        } else {
            mLastChoice.setBackgroundResource(R.drawable.alternative_unselected_background_shape);
            mLastChoice = lastChoice;
        }

    }

    public Fragment setFragmentPosition(int fragmentPosition, Test test, Callbacks.OnQuestionAnsweredListener listener) {
        this.fragmentPosition = fragmentPosition;
        this.mTest = test;
        this.mQuestionAnsweredListener = listener;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_test, container, false);
        bindViews(view);

        if (mTest != null) {
            Question mCurrentQuestion = mTest.getQuestions().get(fragmentPosition);
            AnswerTest questionYetAnswered = ((AnswerTestActivity) getActivity()).isQuestionYetAnswered(fragmentPosition);

            if (mCurrentQuestion != null) {

                setQuestionCounter();

                setQuestionMsgs(mCurrentQuestion);

                setAlternativesVisibility(mCurrentQuestion);

                verifyIfAlternativeAlreadyMarked(view, mCurrentQuestion, questionYetAnswered);
            }
        }
        return view;
    }

    private void verifyIfAlternativeAlreadyMarked(View view, Question mCurrentQuestion, AnswerTest questionYetAnswered) {
        if (questionYetAnswered != null) {
            for (Question.Alternative alternative : mCurrentQuestion.getAlternatives()) {
                if (alternative.getId() == questionYetAnswered.getAlternative_id()) {

                    RelativeLayout backGround = null;

                    if (alternative.getLetter().equals("A")) {
                        backGround = (RelativeLayout) view.findViewById(R.id.alternative_a);
                    } else if (alternative.getLetter().equals("B")) {
                        backGround = (RelativeLayout) view.findViewById(R.id.alternative_b);
                    } else if (alternative.getLetter().equals("C")) {
                        backGround = (RelativeLayout) view.findViewById(R.id.alternative_c);
                    } else if (alternative.getLetter().equals("D")) {
                        backGround = (RelativeLayout) view.findViewById(R.id.alternative_d);
                    } else if (alternative.getLetter().equals("E")) {
                        backGround = (RelativeLayout) view.findViewById(R.id.alternative_e);
                    }

                    if (backGround != null) {
                        backGround.setBackgroundResource(R.drawable.alternative_selected_background_shape);
                        setOptUnselected(backGround);
                    }
                }
            }


        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeRemmainingOnEnterFragment = timeRemmaining;
    }

    private void setAnswerParams() {
        if (mAnswerTest == null) {
            mAnswerTest = new AnswerTest();

            mAnswerTest.setTest_question_id(mTest.getQuestions().get(fragmentPosition).getId());
            mAnswerTest.setElapsed_time(timeRemmainingOnEnterFragment - timeRemmaining);

            if (mLastChoice != null) {
                mAnswerTest.setAlternative_id(getAlternativeSelectedId(mLastChoice.getId()));
            }
        }

        mQuestionAnsweredListener.OnQuestionAnswered(mAnswerTest, fragmentPosition);

        showSendLayout();
    }

    private void showSendLayout() {
        ((AnswerTestActivity) getActivity()).showSendLayout(mTest.getQuestions().size());
    }


    public void setQuestionCounter() {
        questionCounterTv.setText((fragmentPosition + 1) + " de " + mTest.getQuestions().size());
    }

    public void setQuestionMsgs(Question question) {
        questionMsgTv.setText(Html.fromHtml(question.getText()), TextView.BufferType.SPANNABLE);
        questionReferenceTv.setText(Html.fromHtml(question.getReference()), TextView.BufferType.SPANNABLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("LOGUE", "fragment -> " + fragmentPosition + " / onResume called");
    }

    public int getAlternativeSelectedId(int viewId) {
        String alternativeLetter = "";
        switch (viewId) {
            case R.id.alternative_a:
                alternativeLetter = "A";
                break;
            case R.id.alternative_b:
                alternativeLetter = "B";
                break;
            case R.id.alternative_c:
                alternativeLetter = "C";
                break;
            case R.id.alternative_d:
                alternativeLetter = "D";
                break;
            case R.id.alternative_e:
                alternativeLetter = "E";
                break;
        }

        for (Question.Alternative a : mTest.getQuestions().get(fragmentPosition).getAlternatives()) {
            if (a.getLetter().equals(alternativeLetter)) {
                return a.getId();
            }
        }
        return -1;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("LOGUE", "fragment -> " + fragmentPosition + " / onPause called");
    }

    private void setAlternativesVisibility(Question mCurrentQuestion) {
        switch (mCurrentQuestion.getAlternatives().size()) {
            case 1:
                alternativeA.setVisibility(View.VISIBLE);
                alternativeB.setVisibility(View.GONE);
                alternativeC.setVisibility(View.GONE);
                alternativeD.setVisibility(View.GONE);
                alternativeE.setVisibility(View.GONE);
                break;

            case 2:
                alternativeA.setVisibility(View.VISIBLE);
                alternativeB.setVisibility(View.VISIBLE);
                alternativeC.setVisibility(View.GONE);
                alternativeD.setVisibility(View.GONE);
                alternativeE.setVisibility(View.GONE);
                break;

            case 3:
                alternativeA.setVisibility(View.VISIBLE);
                alternativeB.setVisibility(View.VISIBLE);
                alternativeC.setVisibility(View.VISIBLE);
                alternativeD.setVisibility(View.GONE);
                alternativeE.setVisibility(View.GONE);
                break;

            case 4:
                alternativeA.setVisibility(View.VISIBLE);
                alternativeB.setVisibility(View.VISIBLE);
                alternativeC.setVisibility(View.VISIBLE);
                alternativeD.setVisibility(View.VISIBLE);
                alternativeE.setVisibility(View.GONE);
                break;

            case 5:
                alternativeA.setVisibility(View.VISIBLE);
                alternativeB.setVisibility(View.VISIBLE);
                alternativeC.setVisibility(View.VISIBLE);
                alternativeD.setVisibility(View.VISIBLE);
                alternativeE.setVisibility(View.VISIBLE);
                break;
        }

        setAlternativesTexts(mCurrentQuestion);
    }

    private void setAlternativesTexts(Question mCurrentQuestion) {
        for (Question.Alternative a : mCurrentQuestion.getAlternatives()) {
            if (a.getLetter().equals("A")) {
                alternativeALetter.setText("A");
                alternativeATxt.setText(Html.fromHtml(a.getText()), TextView.BufferType.SPANNABLE);
            } else if (a.getLetter().equals("B")) {
                alternativeBLetter.setText("B");
                alternativeBTxt.setText(Html.fromHtml(a.getText()), TextView.BufferType.SPANNABLE);
            } else if (a.getLetter().equals("C")) {
                alternativeCLetter.setText("C");
                alternativeCTxt.setText(Html.fromHtml(a.getText()), TextView.BufferType.SPANNABLE);
            } else if (a.getLetter().equals("D")) {
                alternativeDLetter.setText("D");
                alternativeDTxt.setText(Html.fromHtml(a.getText()), TextView.BufferType.SPANNABLE);
            } else if (a.getLetter().equals("E")) {
                alternativeELetter.setText("E");
                alternativeETxt.setText(Html.fromHtml(a.getText()), TextView.BufferType.SPANNABLE);
            }
        }
    }

    @Override
    public void onMillisUntilFinished(long millisUntilFinished) {
        timeRemmaining = millisUntilFinished;
    }

}
