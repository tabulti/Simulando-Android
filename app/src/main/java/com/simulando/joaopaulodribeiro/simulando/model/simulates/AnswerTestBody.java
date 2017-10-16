package com.simulando.joaopaulodribeiro.simulando.model.simulates;

import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 13/10/2017.
 */

public class AnswerTestBody {
    List<AnswerTest> answers;

    public AnswerTestBody(List<AnswerTest> answers) {
        this.answers = answers;
    }

    public List<AnswerTest> getAnswers() {
        return answers;
    }
}
