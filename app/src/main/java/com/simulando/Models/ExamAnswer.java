package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luciano José on 27/02/2017.
 */

public class ExamAnswer {

    @SerializedName("exam_id")
    public String id;
    @SerializedName("elapsed_time")
    public long elapsedTime;
    @SerializedName("answer_date")
    public Date answerDate;
    public ArrayList<Answer> answers;

    public ExamAnswer() {
        this.answers = new ArrayList<>();
    }
}
