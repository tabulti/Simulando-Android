package com.simulando.Models;

/**
 * Created by Luciano José on 22/01/2017.
 */

public class Session {

    public String token;
    public User user;

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
