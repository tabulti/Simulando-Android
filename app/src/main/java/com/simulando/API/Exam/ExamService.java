package com.simulando.API.Exam;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.simulando.API.Questions.QuestionsAPIConsts;
import com.simulando.API.Questions.QuestionsService;
import com.simulando.Interfaces.APICallback;
import com.simulando.Manager.ApiManager;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.Exam;
import com.simulando.Models.Question;
import com.simulando.Utils.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luciano José on 31/01/2017.
 */

public class ExamService {

    HashMap<String, String> headers;
    private String token;

    private static ExamService mInstance;
    private SessionManager mSessionManager;
    private Gson mResponseManager;
    private ApiManager mApiManager;

    public static ExamService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ExamService(context);
        }
        return mInstance;
    }

    public ExamService(Context context) {
        mSessionManager = SessionManager.getInstance(context);
        mApiManager = ApiManager.getInstance(context);
        mResponseManager = new Gson();
    }

    public void getExams(final APICallback callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ExamAPIConsts.ENDPOINT_EXAM_INFO,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray content = null;

                        try {
                            content = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Type listType = new TypeToken<ArrayList<Exam>>(){}.getType();
                        ArrayList<Exam> exams = mResponseManager.fromJson(content.toString(), listType);

                        callback.onSuccess(exams);
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
