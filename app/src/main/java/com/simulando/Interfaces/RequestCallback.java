package com.simulando.Interfaces;

import com.simulando.Models.User;

/**
 * Created by Luciano José on 19/12/2016.
 */

public interface RequestCallback {

    void onLoginSuccess(User user);

}
