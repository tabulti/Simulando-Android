package com.certimais.API.Questions;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.certimais.Interfaces.APICallback;
import com.certimais.Manager.ApiManager;
import com.certimais.Manager.SessionManager;
import com.certimais.Models.Question;
import com.certimais.Utils.APIUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class QuestionsService {

    HashMap<String, String> headers;
    private String token;

    private static QuestionsService mInstance;
    private SessionManager mSessionManager;
    private Gson mResponseManager;
    private ApiManager mApiManager;

    public static QuestionsService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new QuestionsService(context);
        }
        return mInstance;
    }

    public QuestionsService(Context context) {
        mSessionManager = SessionManager.getInstance(context);
        mApiManager = ApiManager.getInstance(context);
        mResponseManager = new Gson();
    }

    public void getRandomQuestion(final APICallback callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                QuestionsAPIConsts.ENDPOINT_RANDOM_QUESTION,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject content = null;

                        try {
                            content = response.getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Question question = mResponseManager.fromJson(content.toString(), Question.class);
                        callback.onSuccess(question);
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

        mApiManager.addToRequestQueue(request);

    }


}
