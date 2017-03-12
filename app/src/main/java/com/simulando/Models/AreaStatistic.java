package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 07/03/2017.
 */

public class AreaStatistic {

    public String id;
    public String name;
    public float grade;
    @SerializedName("correct_answers")
    public int correctAnswers;
    @SerializedName("wrong_answers")
    public int wrongAnswers;

    public AreaStatistic(String id, String name, float grade, int correctAnswers, int wrongAnswers) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }
}
