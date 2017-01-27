package com.simulando.API.Answer;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.Interfaces.APICallback;
import com.simulando.Manager.ApiManager;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.Answer;
import com.simulando.Models.GenericResponse;
import com.simulando.Utils.APIUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class AnswerService {

    private String token;
    private HashMap<String, String> headers;

    private SessionManager mSessionManager;
    private static AnswerService mInstance;
    private Gson mResponseManager;
    private ApiManager mApiManager;

    public static AnswerService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AnswerService(context);
        }
        return mInstance;
    }

    public AnswerService(Context context) {
        mSessionManager = SessionManager.getInstance(context);
        mApiManager = ApiManager.getInstance(context);
        mResponseManager = new Gson();
    }

    public void registerAnswer(Answer answer, final APICallback callback) {
        Map<String, String> params;
        params = APIUtils.getParams(answer);

        JsonObjectRequest registerAnswerReq = new JsonObjectRequest(Request.Method.POST,
                AnswerAPIConsts.ANSWER_ENDPOINT,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GenericResponse resp = mResponseManager.fromJson(response.toString(), GenericResponse.class);
                        callback.onSuccess(resp);

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


        mApiManager.addToRequestQueue(registerAnswerReq);

    }


}
