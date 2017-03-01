package com.simulando.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.GenericArrayList;
import com.simulando.Models.GenericResponse;
import com.simulando.Utils.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Luciano José on 26/02/2017.
 */

public class Rest {

    private Context mContext;
    private Gson mResponseManager;
    private SessionManager mSessionManager;
    private Class responseClassType;

    private JSONObject objectResponse;
    private JSONArray arrayResponse;

    private int method;
    private String url;
    private String token;
    private Callback callback;

    private Map<String, String> headers;
    private JsonObjectRequest mRequest;
    private JSONObject body;

    public Rest(Context context, Class responseType) {
        this.mContext = context;

        if (mSessionManager == null) {
            mSessionManager = SessionManager.getInstance(mContext);
        }

        if (mResponseManager == null) {
            mResponseManager = new Gson();
        }

        this.responseClassType = responseType;
    }

    public Rest get(String url) {
        this.method = com.android.volley.Request.Method.GET;
        this.url = url;
        this.body = null;
        return this;
    }

    public Rest one(String url, String id) {
        this.method = com.android.volley.Request.Method.GET;
        this.url = url.replace(APIConsts.PATH_PARAM, id);
        this.body = null;
        return this;
    }

    public Rest post(String url, Object body) {
        this.method = com.android.volley.Request.Method.POST;
        this.url = url;
        this.body = APIUtils.getBody(body);
        return this;
    }

    public Rest setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public JsonObjectRequest build() {
        mRequest = new JsonObjectRequest(this.method,
                this.url,
                this.body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = null;
                        Object object = null;

                        try {
                            status = response.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (status.equals("success")) {
                            object = parseResponse(response);
                        }

                        callback.onSuccess(object);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("ERROR");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                token = mSessionManager.getToken();
                headers = APIUtils.getHeaders(token);
                return headers;
            }
        };

        return mRequest;
    }

    public Object parseResponse(JSONObject response) {
        Object object;

        if (response.has("token") || responseClassType.equals(GenericResponse.class)) {
            object = mResponseManager.fromJson(response.toString(), responseClassType);
        } else {
            objectResponse = response.optJSONObject("data");

            if (objectResponse != null) {
                object = mResponseManager.fromJson(objectResponse.toString(), responseClassType);
            } else {
                arrayResponse = response.optJSONArray("data");
                object = mResponseManager.fromJson(arrayResponse.toString(), new GenericArrayList<>(responseClassType));
            }
        }
        return object;
    }
}
