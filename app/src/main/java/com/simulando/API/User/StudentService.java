package com.simulando.API.User;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.API.Rest;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Profile;
import com.simulando.Models.Ranking;
import com.simulando.Models.RankingRow;
import com.simulando.Models.Session;
import com.simulando.Models.UserAuthInfo;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class StudentService {

    private static StudentService mInstance;
    private Context mContext;
    private ApiManager mApiManager;

    public static StudentService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new StudentService(context);
        }
        return mInstance;
    }

    public StudentService(Context context) {
        this.mContext = context;
        mApiManager = ApiManager.getInstance(context);
    }

    public void getProfile(String userId, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Profile.class)
                .one(StudentAPIConsts.ENDPOINT_STUDENT_PROFILE, userId)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void getRank(final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, RankingRow.class)
                .get(StudentAPIConsts.ENDPOINT_STUDENTS_RANK)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void registerUser(final UserAuthInfo userAuthInfo, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Session.class)
                .post(StudentAPIConsts.ENDPOINT_USER, userAuthInfo)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

    public void authUser(UserAuthInfo info, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Session.class)
                .post(StudentAPIConsts.ENDPOINT_AUTH_USER, info)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);
    }

}
