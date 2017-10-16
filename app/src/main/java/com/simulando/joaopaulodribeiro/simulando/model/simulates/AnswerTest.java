package com.simulando.joaopaulodribeiro.simulando.model.simulates;

/**
 * Created by joao.paulo.d.ribeiro on 13/10/2017.
 */

public class AnswerTest {
    public Integer test_question_id;
    public Integer alternative_id;
    public long elapsed_time;
    public String answer_date;

    public AnswerTest() {}

    public Integer getTest_question_id() {
        return test_question_id;
    }

    public void setTest_question_id(Integer test_question_id) {
        this.test_question_id = test_question_id;
    }

    public Integer getAlternative_id() {
        return alternative_id;
    }

    public void setAlternative_id(Integer alternative_id) {
        this.alternative_id = alternative_id;
    }

    public long getElapsed_time() {
        return elapsed_time;
    }

    public void setElapsed_time(long elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public String getAnswer_date() {
        return answer_date;
    }

    public void setAnswer_date(String answer_date) {
        this.answer_date = answer_date;
    }
}
