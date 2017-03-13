package com.simulando.API.Exam;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.API.Rest;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Exam;
import com.simulando.Models.ExamAnswer;
import com.simulando.Models.ExamStatistic;
import com.simulando.Models.GenericResponse;

/**
 * Created by Luciano José on 31/01/2017.
 */

public class ExamService {

    private Context mContext;
    private static ExamService mInstance;
    private ApiManager mApiManager;

    public static ExamService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ExamService(context);
        }
        return mInstance;
    }

    public ExamService(Context context) {
        this.mContext = context;
        mApiManager = ApiManager.getInstance(context);
    }

    public void getExams(final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Exam.class)
                .get(ExamAPIConsts.ENDPOINT_EXAMS)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void getExam(String examId, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Exam.class)
                .one(ExamAPIConsts.ENDPOINT_EXAM_INFO, examId)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void getExamResult(String examId, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, ExamStatistic.class)
                .one(ExamAPIConsts.ENDPOINT_EXAM_RESULT, examId)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void registerAnswer(ExamAnswer examAnswer, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, GenericResponse.class)
                .post(ExamAPIConsts.ENDPOINT_ANSWER_EXAM, examAnswer)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void cancelExam(String examId, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, GenericResponse.class)
                .put(ExamAPIConsts.ENDPOINT_CANCEL_EXAM, examId)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }
}
