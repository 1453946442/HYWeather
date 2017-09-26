package com.hx.weather.hyweather.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.hx.weather.hyweather.bean.NowWeatherBean;
import com.hx.weather.hyweather.rawbean.WeatherRawBean;
import com.hx.weather.hyweather.contact.CityListContact;
import com.hx.weather.hyweather.contact.CommonContact;
import com.hx.weather.hyweather.model.WeatherModel;
import com.hx.weather.hyweather.util.SPUtil;
import com.hx.weather.hyweather.view.activity.ShowCityListActivity;

/**
 * Created by win10 on 2017/9/21.
 */

public class CityListPresenter implements CityListContact.CityListPresenter {
    private CityListContact.CityListView mActivity;
    private CommonContact.Model mModel;
    private NowWeatherBean nowWeatherBean;

    @Override
    public void attachView(@NonNull ShowCityListActivity view) {
        mActivity = view;
        mModel = new WeatherModel(view.getApplicationContext());
    }

    @Override
    public void delete() {

    }

    @Override
    public void returnCityId(String cityId) {
        mActivity.returnMainPage(cityId);
    }

    @Override
    public void getNowData(String[] temp) {
        for(int i=0;i<temp.length;i++) {
            mModel.setCityId(temp[i]);
            mModel.getData(new CommonContact.CommonCallback() {
                @Override
                public void onResponse(String s) {
                    Gson gson = new Gson();
                    WeatherRawBean weatherRawBean = gson.fromJson(s, WeatherRawBean.class);
                    nowWeatherBean = NowWeatherBean.getChange(weatherRawBean);
                    mActivity.setAdapter(nowWeatherBean);
                    mActivity.notifyAdapter();
                }
            });
        }
    }

    @Override
    public void detachView() {
        mActivity = null;
    }

    @Override
    public void operateCityId(String cityId) {
        if(SPUtil.getString("list").equals("")) {
            SPUtil.putString("list", cityId);
        } else {
            SPUtil.putString("list", SPUtil.getString("list") + "," + cityId);
        }
    }
}
