package com.simulando.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luciano José on 29/01/2017.
 */

public class Exam {

    public String id;
    public String title;
    public String description;
    public String time;
    public int limit;
    public Date exam_start_date;
    public Date exam_end_date;
    public ArrayList<Question> questions;


    public String getQuestionsNumber() {
        return this.limit + " Questões";
    }
}
