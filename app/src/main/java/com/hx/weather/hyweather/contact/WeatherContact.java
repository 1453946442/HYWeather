package com.hx.weather.hyweather.contact;

import android.support.annotation.NonNull;

import com.hx.weather.hyweather.view.activity.MainActivity;

/**
 * Created by win10 on 2017/9/18.
 */

public interface WeatherContact {
    interface WeatherView extends CommonContact.View {}

    interface WeatherPresenter extends CommonContact.Presenter {
        void attachView(@NonNull MainActivity view);

        void setCityId(String cityId);
        void getData();

        void addCityId(String cityId);
    }

}
