package com.simulando.joaopaulodribeiro.simulando.model.simulates;

import android.content.Intent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 02/10/2017.
 */

public class Question implements Serializable{
    Integer id;
    String title;
    String text;
    String statement;
    String reference;
    String image_url;
    String image_position;
    String answer;
    Integer year;
    Integer reward;
    Integer penalty;
    String question_site_url;
    String question_site_id;
    Integer subject_id;
    List<Alternative> alternatives;

    public Question(Integer id, String title, String text, String statement, String reference,
                    String image_url, String image_position, String answer, Integer year,
                    Integer reward, Integer penalty, String question_site_url,
                    String question_site_id, Integer subject_id, List<Alternative> alternatives) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.statement = statement;
        this.reference = reference;
        this.image_url = image_url;
        this.image_position = image_position;
        this.answer = answer;
        this.year = year;
        this.reward = reward;
        this.penalty = penalty;
        this.question_site_url = question_site_url;
        this.question_site_id = question_site_id;
        this.subject_id = subject_id;
        this.alternatives = alternatives;
    }
    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getStatement() {
        return statement;
    }

    public String getReference() {
        return reference;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getImage_position() {
        return image_position;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getReward() {
        return reward;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public String getQuestion_site_url() {
        return question_site_url;
    }

    public String getQuestion_site_id() {
        return question_site_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    /********************************************************************/

    public class Alternative implements Serializable{
        Integer id;
        String letter;
        String text;
        Integer question_id;

        public Alternative(Integer id, String letter, String text, Integer question_id) {
            this.id = id;
            this.letter = letter;
            this.text = text;
            this.question_id = question_id;
        }

        public Integer getId() {
            return id;
        }

        public String getLetter() {
            return letter;
        }

        public String getText() {
            return text;
        }

        public Integer getQuestion_id() {
            return question_id;
        }
    }
}
