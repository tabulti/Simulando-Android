package com.simulando.Utils;

import android.util.Log;

import com.simulando.API.APIConsts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Luciano José on 15/01/2017.
 */

public class APIUtils {

    static Gson mJsonManager = new Gson();
    static final HashMap<String, String> headers = new HashMap<String, String>();

    public static JSONObject getBody(Object object) {
        JSONObject body = null;
        try {
            body = new JSONObject(mJsonManager.toJson(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return body;
    }

    public static HashMap<String, String> getHeaders(String token) {
        headers.put(APIConsts.HEADER_CONTENT_TYPE, APIConsts.HEADER_CONTENT_TYPE_VALUE);
        headers.put(APIConsts.HEADER_ACCEPT, APIConsts.HEADER_ACCEPT_VALUE);
        headers.put(APIConsts.HEADER_AUTHORIZATION, token);
        return headers;
    }

    public static HashMap<String, String> getHeaders() {
        headers.put(APIConsts.HEADER_CONTENT_TYPE, APIConsts.HEADER_CONTENT_TYPE_VALUE);
        headers.put(APIConsts.HEADER_ACCEPT, APIConsts.HEADER_ACCEPT_VALUE);
        return headers;
    }


}
