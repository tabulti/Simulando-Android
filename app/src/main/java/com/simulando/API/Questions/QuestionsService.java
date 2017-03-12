package com.simulando.API.Questions;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.API.Rest;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Question;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class QuestionsService {

    private Context mContext;
    private static QuestionsService mInstance;
    private ApiManager mApiManager;

    public static QuestionsService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new QuestionsService(context);
        }
        return mInstance;
    }

    public QuestionsService(Context context) {
        this.mContext = context;
        mApiManager = ApiManager.getInstance(context);
    }

    public void getRandomQuestion(final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Question.class)
                .get(QuestionsAPIConsts.ENDPOINT_RANDOM_QUESTION)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }


}
