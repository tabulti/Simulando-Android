package com.simulando.UI.Dashboard.Exams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.simulando.API.Exam.ExamService;
import com.simulando.Interfaces.APICallback;
import com.simulando.R;

/**
 * Created by Luciano José on 16/02/2017.
 */

public class AnswerExamActivity extends AppCompatActivity {

    ExamService mExamService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        String examId = getIntent().getStringExtra("examId");

        mExamService = ExamService.getInstance(this);
        getExamInfo(examId);
    }

    /**
     * Busca as informações do simulado
     * para inicia-lo.
     * @param examId
     */
    public void getExamInfo(String examId){
        mExamService.getExam(examId, new APICallback() {
            @Override
            public void onSuccess(Object response) {
                Log.d("RESP", response.toString());
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * Atualiza as informações da Questão
     */
    public void updateQuestionInfo(){

    }

    /**
     * Adiciona a resposta da questão
     * atual no array para ser cadastrado
     * ao final do simulado.
     */
    public void addAnswer(){

    }
}
