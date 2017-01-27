package com.simulando.API.User;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.Interfaces.APICallback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Session;
import com.simulando.Models.UserAuthInfo;
import com.simulando.Models.User;
import com.simulando.Utils.APIUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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


    public void registerUser(final UserAuthInfo userAuthInfo, final APICallback callback) {

        Map<String, String> params;
        params = APIUtils.getParams(userAuthInfo);

        Log.d("PA", params.toString());
        Log.d("US", userAuthInfo.toString());

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

    public void authUser(UserAuthInfo info, final APICallback callback) {
        Map<String, String> params = APIUtils.getParams(info);

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
