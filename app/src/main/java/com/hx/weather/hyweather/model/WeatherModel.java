package com.hx.weather.hyweather.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hx.weather.hyweather.net.WeatherRoute;
import com.hx.weather.hyweather.contact.CommonContact;

/**
 * Created by win10 on 2017/9/19.
 */

public class WeatherModel implements CommonContact.Model {
    private String cityId;
    private RequestQueue wQueue;

    public WeatherModel(Context context) {
        wQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public void getData(final CommonContact.CommonCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WeatherRoute.WEATHER_BYCITY + cityId, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(callback != null) {
                    callback.onResponse(s);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });
        wQueue.add(stringRequest);
    }

    @Override
    public void getCityData(final CommonContact.CommonCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WeatherRoute.SEARCH_BYCITY + cityId, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onResponse(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });
        wQueue.add(stringRequest);
    }

    @Override
    public void testCity(final CommonContact.CommonCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WeatherRoute.WEATHER_BYCITY + cityId, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onResponse("true");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                callback.onResponse("false");
            }
        });
        wQueue.add(stringRequest);
    }
}
