package com.simulando.Models;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Luciano José on 16/03/2017.
 */

public class BulkAnswers {

    List<Answer> answers;

    public BulkAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
