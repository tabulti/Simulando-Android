package com.simulando.API.Answer;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.API.Rest;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Answer;
import com.simulando.Models.GenericResponse;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class AnswerService {

    private Context mContext;
    private static AnswerService mInstance;
    private ApiManager mApiManager;

    public static AnswerService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AnswerService(context);
        }
        return mInstance;
    }

    public AnswerService(Context context) {
        this.mContext = context;
        mApiManager = ApiManager.getInstance(context);
    }

    public void registerAnswer(Answer answer, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, GenericResponse.class)
                .post(AnswerAPIConsts.ANSWER_ENDPOINT, answer)
                .setCallback(callback)
                .build();


        mApiManager.addToRequestQueue(mRequest);

    }


}
