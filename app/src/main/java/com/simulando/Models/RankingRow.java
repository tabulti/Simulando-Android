package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 12/02/2017.
 */

public class RankingRow {

    @SerializedName("student_id")
    public String studentId;
    public String name;
    public int points;
    public int position;
    @SerializedName("profile_picture")
    public String profilePicture;

    public int getPosition() {
        return position;
    }
}
