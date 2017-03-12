package com.simulando.API.User;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.simulando.API.Rest;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.ApiManager;
import com.simulando.Models.Session;
import com.simulando.Models.UserAuthInfo;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class UserService {

    private static UserService mInstance;
    private Context mContext;
    private ApiManager mApiManager;

    public static UserService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserService(context);
        }
        return mInstance;
    }

    public UserService(Context context) {
        this.mContext = context;
        mApiManager = ApiManager.getInstance(context);
    }

    public void registerUser(final UserAuthInfo userAuthInfo, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Session.class)
                .post(UserAPIConsts.ENDPOINT_USER, userAuthInfo)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);

    }

    public void authUser(UserAuthInfo info, final Callback callback) {
        JsonObjectRequest mRequest = new Rest(mContext, Session.class)
                .post(UserAPIConsts.ENDPOINT_AUTH_USER, info)
                .setCallback(callback)
                .build();

        mApiManager.addToRequestQueue(mRequest);

    }

}
