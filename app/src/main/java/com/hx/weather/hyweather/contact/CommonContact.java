package com.hx.weather.hyweather.contact;

import android.support.annotation.NonNull;

import com.hx.weather.hyweather.bean.CommonBean;

/**
 * Created by win10 on 2017/9/21.
 */

public interface CommonContact {
    interface View {
        void showToast(String string);

        void setAdapter(CommonBean commonBean);

        void notifyAdapter();

        void stopRefresh();
    }

    interface Model {
        void setCityId(String cityId);
        String getCityId();

//        void getNowData(CommonContact.CommonCallback callback);
//        void getDailyData(CommonContact.CommonCallback callback);
//        void getSuggestionData(CommonContact.CommonCallback callback);

        void getData(CommonCallback callback);

        void getCityData(CommonCallback callback);
        void testCity(CommonCallback callback);
    }

    interface Presenter {
        void detachView();

        void addCityId(String cityId);
    }

    interface CommonCallback { void onResponse(String s); }
}
