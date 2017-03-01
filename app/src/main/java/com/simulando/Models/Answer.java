package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class Answer {

    @SerializedName("alternative_id")
    public String alternativeId;
    @SerializedName("elapsed_time")
    public long elapsedTime;
    public char answer;

    public Answer(String alternativeId, char answer, long elapsedTime) {
        this.alternativeId = alternativeId;
        this.answer = answer;
        this.elapsedTime = elapsedTime;
    }

}

