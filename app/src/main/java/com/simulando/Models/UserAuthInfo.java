package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class UserAuthInfo {

    public final boolean social;
    public final String name;
    public final String cpf;
    public final String email;
    public final String password;
    @SerializedName("profile_picture")
    public final String profilePicture;
    public final String language;

    public UserAuthInfo(boolean social, String name, String cpf, String email, String password, String profilePicture, String language) {
        this.social = social;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.language = language;
    }

}
