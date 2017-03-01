package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Luciano José on 27/02/2017.
 */

public class ExamAnswer {

    @SerializedName("exam_id")
    public String id;
    public ArrayList<Answer> answers;

    public ExamAnswer() {
        this.answers = new ArrayList<>();
    }
}
