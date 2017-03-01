package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class Question {

    public String id;
    public String statement;
    public char answer;
    public String year;
    @SerializedName("question_number")
    public String questionNumber;
    @SerializedName("score_value")
    public int score;
    @SerializedName("alternative")
    public List<Alternative> alternatives;

}
