package com.certimais.Manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class ApiManager {

    private static ApiManager mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private ApiManager(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ApiManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ApiManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
