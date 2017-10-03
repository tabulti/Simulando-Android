package com.simulando.joaopaulodribeiro.simulando.model.simulates;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 30/09/2017.
 */

public class Test implements Serializable{
    private Integer id;
    private String title;
    private String description;
    private String total_questions;
    private String estimated_time;
    private String test_start_date;
    private String test_end_date;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private List<Question> questions;

    public Test(Integer id, String title, String description, String total_questions,
                String estimated_time, String test_start_date, String test_end_date,
                String created_at, String updated_at, String deleted_at, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.total_questions = total_questions;
        this.estimated_time = estimated_time;
        this.test_start_date = test_start_date;
        this.test_end_date = test_end_date;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTotal_questions() {
        return total_questions;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public String getTest_start_date() {
        return test_start_date;
    }

    public String getTest_end_date() {
        return test_end_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}