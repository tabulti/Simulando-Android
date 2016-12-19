package com.certimais.UI.Dashboard.AnswerQuestions;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.certimais.R;


public class AnswerQuestionsFragment extends Fragment {


    public AnswerQuestionsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_questions, container, false);
        return view;
    }

}
