package com.simulando.UI.Dashboard.Questions.QuestionFeedback;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.simulando.Consts.AppConsts;
import com.simulando.R;

public class FeedbackDialogFragment extends DialogFragment {

    FinishDialogListener finishDialogListener;
    int points;
    int mFeedbackIcon;
    int mFeedbackText;
    int mFeedbackPoints;

    private ImageView mIvIconFeedback;
    private Button mBtnLoadQuestion;
    private ImageButton mBtnClose;
    private TextView mTvFeedbackText;
    private TextView mTvFeedbackBox;
    private TextView mTvFeedbackPoints;

    View.OnClickListener btnCloseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finishDialogListener.onFinishDialog(AppConsts.CLOSE_DIALOG_ACTION);
            dismiss();
        }
    };

    View.OnClickListener btnLoadQuestionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finishDialogListener.onFinishDialog(AppConsts.LOAD_QUESTION_ACTION);
            dismiss();
        }
    };

    public FeedbackDialogFragment() {
        super();
    }

    public static FeedbackDialogFragment newInstance() {
        return new FeedbackDialogFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_fragment_layout, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        finishDialogListener = (FinishDialogListener) getActivity();

        mIvIconFeedback = (ImageView) view.findViewById(R.id.feedbackIcon);
        mTvFeedbackText = (TextView) view.findViewById(R.id.feedbackText);
        mTvFeedbackBox = (TextView) view.findViewById(R.id.feedbackBox);
        mTvFeedbackPoints = (TextView) view.findViewById(R.id.feedbackPoints);
        mBtnLoadQuestion = (Button) view.findViewById(R.id.nextQuestion);
        mBtnClose = (ImageButton) view.findViewById(R.id.closeButton);

        mBtnLoadQuestion.setOnClickListener(btnLoadQuestionListener);
        mBtnClose.setOnClickListener(btnCloseListener);

        updateInfo(this.points);

    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        super.onResume();

    }

    public void setDialogInfo(boolean isCorrect, int points) {
        if (isCorrect) {
            mFeedbackIcon = R.drawable.emoticon_correct;
            mFeedbackText = R.string.feedback_text_correct;
            mFeedbackPoints = R.string.earned_points;
        } else {
            mFeedbackIcon = R.drawable.emoticon_wrong;
            mFeedbackText = R.string.feedback_text_incorrect;
            mFeedbackPoints = R.string.lost_points;
        }
        this.points = points;
    }

    public void updateInfo(int points) {
        String feedbackPoints = getResources().getString(mFeedbackPoints, points);
        if (mFeedbackPoints == R.string.lost_points) {
            mTvFeedbackPoints.setTextColor(getResources().getColor(R.color.red));
        }else{
            mTvFeedbackPoints.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        mTvFeedbackText.setText(mFeedbackText);
        mTvFeedbackPoints.setText(feedbackPoints);
        mTvFeedbackBox.setText(feedbackPoints);
        mIvIconFeedback.setImageResource(mFeedbackIcon);
    }

    public interface FinishDialogListener {
        void onFinishDialog(int action);
    }

}
