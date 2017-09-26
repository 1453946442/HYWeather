package com.hx.weather.hyweather.contact;

import android.support.annotation.NonNull;

import com.hx.weather.hyweather.view.activity.SearchActivity;

/**
 * Created by win10 on 2017/9/21.
 */

public interface SearchContact {
    interface SearchView extends CommonContact.View {
        void returnMainPage(String cityId);
    }

    interface SearchPresenter extends CommonContact.Presenter {
        void attachView(@NonNull SearchActivity view);

        void setCityId(String cityId);
        void getCityData();
        void testCity();
    }
}
