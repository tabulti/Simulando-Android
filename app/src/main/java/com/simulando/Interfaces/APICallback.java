package com.simulando.Interfaces;

/**
 * Created by Luciano José on 08/01/2017.
 */

public interface APICallback {

    void onSuccess(Object response);
    void onError(String message);

}