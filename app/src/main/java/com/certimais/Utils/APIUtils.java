package com.certimais.Utils;

import com.bumptech.glide.load.model.Headers;
import com.certimais.API.APIConsts;
import com.certimais.Manager.SessionManager;
import com.certimais.Models.GenericResponse;
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

    public static HashMap<String, String> getParams(Object object) {
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();

        String json = mJsonManager.toJson(object);
        HashMap<String, String> params = mJsonManager.fromJson(json, type);
        return params;
    }

    public static HashMap<String, String> getHeaders(String token) {
        headers.put(APIConsts.HEADER_CONTENT_TYPE, APIConsts.HEADER_CONTENT_TYPE_VALUE);
        headers.put(APIConsts.HEADER_ACCEPT, APIConsts.HEADER_ACCEPT_VALUE);
        headers.put(APIConsts.HEADER_AUTHORIZATION, token);
        return headers;
    }

}
