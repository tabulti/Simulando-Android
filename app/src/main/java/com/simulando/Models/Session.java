package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 22/01/2017.
 */

public class Session {

    public String token;
    @SerializedName("data")
    public User user;

}
