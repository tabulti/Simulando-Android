package com.certimais.API.User;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.certimais.Interfaces.APICallback;
import com.certimais.Manager.ApiManager;
import com.certimais.Models.Question;
import com.certimais.Models.RegisterInfo;
import com.certimais.Models.Session;
import com.certimais.Models.User;
import com.certimais.Utils.APIUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class UserService {

    private static UserService mInstance;
    private Gson mResponseManager;
    private ApiManager mApiManager;

    public static UserService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserService(context);
        }
        return mInstance;
    }

    public UserService(Context context) {
        mApiManager = ApiManager.getInstance(context);
        mResponseManager = new Gson();
    }


    public void registerUser(final RegisterInfo registerInfo, final APICallback callback) {

        Map<String, String> params;
        params = APIUtils.getParams(registerInfo);

        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST,
                UserAPIConsts.ENDPOINT_USER,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("ERROR");
                    }
                });


        mApiManager.addToRequestQueue(registerRequest);

    }

    public void authUser(final String email, final String password, final APICallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("senha", password);

        JsonObjectRequest authRequest = new JsonObjectRequest(Request.Method.POST,
                UserAPIConsts.ENDPOINT_AUTH_USER,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject content = null;
                        try {
                            content = response.getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Session session = new Session();
                        try {
                            session.user = mResponseManager.fromJson(content.toString(), User.class);
                            session.token = response.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        callback.onSuccess(session);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("ERROR");
                    }
                });


        mApiManager.addToRequestQueue(authRequest);
    }

}
