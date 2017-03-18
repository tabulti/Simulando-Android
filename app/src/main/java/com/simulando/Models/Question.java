package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class Question extends RealmObject {

    public String id;
    public String statement;
    public String answer;
    public String year;
    @SerializedName("question_number")
    public String questionNumber;
    @SerializedName("score_value")
    public int score;
    @SerializedName("alternative")
    public RealmList<Alternative> alternatives;
    public Subject subject;

}
