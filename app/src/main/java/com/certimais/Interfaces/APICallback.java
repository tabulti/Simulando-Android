package com.certimais.Interfaces;

import org.json.JSONObject;

/**
 * Created by Luciano José on 08/01/2017.
 */

public interface APICallback {

    void onSuccess(Object response);
    void onError(String message);

}
