package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 12/03/2017.
 */

public class Profile {


    public String id;
    @SerializedName("student_id")
    public String studentId;
    public int level;
    @SerializedName("current_xp")
    public int currentXp;
    public int rank;
    public int points;
}
