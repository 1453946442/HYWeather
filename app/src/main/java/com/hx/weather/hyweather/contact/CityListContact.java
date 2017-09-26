package com.hx.weather.hyweather.contact;

import android.support.annotation.NonNull;

import com.hx.weather.hyweather.view.activity.ShowCityListActivity;

/**
 * Created by win10 on 2017/9/21.
 */

public interface CityListContact {
    interface CityListView extends CommonContact.View {
        void returnMainPage(String cityId);
    }

    interface CityListPresenter extends CommonContact.Presenter {
        void attachView(@NonNull ShowCityListActivity view);
        void delete();

        void returnCityId(String cityId);
        void getNowData(String[] temp);
    }
}
