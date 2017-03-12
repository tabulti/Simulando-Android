package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class Answer {

    @SerializedName("alternative_id")
    public String alternativeId;
    @SerializedName("elapsed_time")
    public long elapsedTime;
    public char answer;
    @SerializedName("answer_date")
    public Date answerDate;

    public Answer(String alternativeId, char answer, long elapsedTime, Date answerDate) {
        this.alternativeId = alternativeId;
        this.answer = answer;
        this.elapsedTime = elapsedTime;
        this.answerDate = answerDate;
    }

}

