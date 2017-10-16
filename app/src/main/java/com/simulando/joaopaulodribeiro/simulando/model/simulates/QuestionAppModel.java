package com.simulando.joaopaulodribeiro.simulando.model.simulates;

/**
 * Created by joao.paulo.d.ribeiro on 15/10/2017.
 */

/**
 * Class that models question fragment with an id to order this fragment in a array
 */
public class QuestionAppModel {
    int questionFragmentOrder;
    AnswerTest question;

    public QuestionAppModel(int questionFragmentOrder, AnswerTest question) {
        this.questionFragmentOrder = questionFragmentOrder;
        this.question = question;
    }

    public int getQuestionFragmentOrder() {
        return questionFragmentOrder;
    }

    public AnswerTest getQuestion() {
        return question;
    }
}
