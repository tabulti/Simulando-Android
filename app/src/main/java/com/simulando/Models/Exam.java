package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class Exam {

    public String id;
    public String title;
    public String description;
    public boolean done;
    @SerializedName("estimated_time")
    public String estimatedTime;
    @SerializedName("questions_number")
    public int questionsNumber;
    @SerializedName("exam_start_date")
    public Date startDate;
    @SerializedName("exam_end_date")
    public Date endDate;
    public ArrayList<Question> questions;


    public String getQuestionsNumber() {
        return this.questionsNumber + " Questões";
    }

}
