package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 18/12/2016.
 */

public class User {

    public String id;
    public String name;
    public String email;
    public String cpf;
    @SerializedName("student_id")
    public String studentId;
    @SerializedName("profile_picture")
    public String profilePicture;

}
