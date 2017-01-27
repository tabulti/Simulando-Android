package com.simulando.Manager;

import android.content.Context;

import com.simulando.Consts.LoginConsts;
import com.simulando.Models.User;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

/**
 * Created by Luciano José on 18/12/2016.
 */
public class SessionManager {

    private static SessionManager instance;
    private PreferenceManager preferenceManager;
    private Gson gson;

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }

        return instance;
    }

    private SessionManager(Context context) {
        preferenceManager = new PreferenceManager(context);
        gson = new Gson();
    }

    public void setCurrentUser(User user, String loginType, String token) {
        preferenceManager.putBoolean(LoginConsts.IS_LOGGED, true);
        preferenceManager.putString(LoginConsts.LOGIN_TYPE, loginType);
        preferenceManager.putString(LoginConsts.CURRENT_USER, gson.toJson(user));
        preferenceManager.putString(LoginConsts.TOKEN, token);
    }

    public User getCurrentUser() {
        String user = preferenceManager.getString(LoginConsts.CURRENT_USER);
        User currentUser = gson.fromJson(user, User.class);
        return currentUser;
    }

    public void setToken(String token) {
        preferenceManager.putString(LoginConsts.TOKEN, token);
    }

    public String getToken() {
        String token = preferenceManager.getString(LoginConsts.TOKEN);
        return token;
    }

    public boolean isLogged() {
        return preferenceManager.getBoolean(LoginConsts.IS_LOGGED);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        preferenceManager.putBoolean(LoginConsts.IS_LOGGED, false);
        preferenceManager.clear();
    }

}
