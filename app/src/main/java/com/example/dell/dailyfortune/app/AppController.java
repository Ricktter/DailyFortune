package com.example.dell.dailyfortune.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by Ricktter on 01/12/2015.
 */
public class AppController extends Application {
    private static final String TAG = "AppController";
    private static AppController ourInstance = new AppController();

    public static AppController getInstance() {
        return ourInstance;
    }

    private AppController() {
    }

    private RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
