package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luciano José on 07/03/2017.
 */

public class ExamStatistic {

    @SerializedName("exam_title")
    public String examTitle;
    @SerializedName("questions_number")
    public int questionsNumber;
    @SerializedName("answer_date")
    public Date answerDate;
    public float average;
    @SerializedName("total_correct_answers")
    public int totalCorrectAnswers;
    @SerializedName("total_wrong_answers")
    public int totalWrongAnswers;
    public ArrayList<AreaStatistic> statistic;

    public ExamStatistic() {
        this.statistic = new ArrayList<>();
    }
}
