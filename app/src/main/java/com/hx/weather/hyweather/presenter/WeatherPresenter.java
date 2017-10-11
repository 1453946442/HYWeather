package com.hx.weather.hyweather.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.hx.weather.hyweather.rawbean.WeatherRawBean;
import com.hx.weather.hyweather.contact.CommonContact;
import com.hx.weather.hyweather.util.SPUtil;
import com.hx.weather.hyweather.view.activity.MainActivity;
import com.hx.weather.hyweather.bean.DailyWeatherBean;
import com.hx.weather.hyweather.bean.NowWeatherBean;
import com.hx.weather.hyweather.bean.SuggestionWeatherBean;
import com.hx.weather.hyweather.model.WeatherModel;
import com.hx.weather.hyweather.contact.WeatherContact;

/**
 * Created by win10 on 2017/9/19.
 */

public class WeatherPresenter implements WeatherContact.WeatherPresenter {
    private WeatherContact.WeatherView mActivity;
    private CommonContact.Model mModel;
    private NowWeatherBean nowWeatherBean;
    private DailyWeatherBean dailyWeatherBean;
    private SuggestionWeatherBean suggestionWeatherBean;

    private final String TAG = "WeatherPresenter";

    @Override
    public void attachView(@NonNull MainActivity view) {
        mActivity = view;
        mModel = new WeatherModel(view.getApplicationContext());
    }

    @Override
    public void detachView() {
        mActivity = null;
    }

    @Override
    public void setCityId(String cityId) {
        mModel.setCityId(cityId);
    }

    @Override
    public void getData() {
        mModel.getData(new CommonContact.CommonCallback() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                WeatherRawBean weatherRawBean = gson.fromJson(s, WeatherRawBean.class);

                nowWeatherBean = NowWeatherBean.getChange(weatherRawBean);
                mActivity.setAdapter(nowWeatherBean);

                dailyWeatherBean = DailyWeatherBean.getChange(weatherRawBean);
                for(int i = 0; i < dailyWeatherBean.getDailys().size(); i++)
                    mActivity.setAdapter(dailyWeatherBean.getDailys().get(i));

                suggestionWeatherBean = SuggestionWeatherBean.getChange(weatherRawBean);
                mActivity.setAdapter(suggestionWeatherBean);

                mActivity.notifyAdapter();
            }
        });
    }

    @Override
    public void addCityId(String cityId) {
        if(SPUtil.getString("list").equals("")) {
            SPUtil.putString("list", cityId);
        } else {
            if (!SPUtil.getString("list").contains(cityId))
                SPUtil.putString("list", SPUtil.getString("list") + "," + cityId);
        }
    }
}
