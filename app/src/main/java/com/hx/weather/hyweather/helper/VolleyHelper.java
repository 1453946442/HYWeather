package com.hx.weather.hyweather.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by win10 on 2017/9/6.
 */

public class VolleyHelper {
    private RequestQueue mQueue;
    private String TAG = "VolleyHelper";

    private volatile static VolleyHelper volleyHelper;
    private VolleyHelper() {}
    public static VolleyHelper getVolleyHelper(Context context) {
        if(volleyHelper == null) {
            synchronized (VolleyHelper.class) {
                if(volleyHelper == null) {
                    volleyHelper = new VolleyHelper();

                    volleyHelper.mQueue = Volley.newRequestQueue(context);
                }
            }
        }
        return volleyHelper;
    }

    /**
     * @params url 请求地址
     */
    public void addStringRequest(String url) {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
    }

    /**
     *
     */
//    public void
}
