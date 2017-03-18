package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class Answer extends RealmObject {

    @SerializedName("alternative_id")
    public String alternativeId;
    @SerializedName("elapsed_time")
    public long elapsedTime;
    public String answer;
    @SerializedName("answer_date")
    public Date answerDate;

    public Answer() {
    }

    public Answer(String alternativeId, String answer, long elapsedTime, Date answerDate) {
        this.alternativeId = alternativeId;
        this.answer = answer;
        this.elapsedTime = elapsedTime;
        this.answerDate = answerDate;
    }

}

