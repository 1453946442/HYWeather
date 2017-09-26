package com.hx.weather.hyweather.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.hx.weather.hyweather.bean.CityBean;
import com.hx.weather.hyweather.contact.CommonContact;
import com.hx.weather.hyweather.contact.SearchContact;
import com.hx.weather.hyweather.rawbean.CityRawBean;
import com.hx.weather.hyweather.model.WeatherModel;
import com.hx.weather.hyweather.util.SPUtil;
import com.hx.weather.hyweather.view.activity.SearchActivity;

/**
 * Created by win10 on 2017/9/19.
 */

public class SearchPresenter implements SearchContact.SearchPresenter {
    private SearchContact.SearchView mActivity;
    private CommonContact.Model mModel;
    private CityBean cityBean;

    @Override
    public void attachView(@NonNull SearchActivity view) {
        mActivity = view;
        mModel = new WeatherModel(view.getApplicationContext());
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

    @Override
    public void setCityId(String cityId) {
        mModel.setCityId(cityId);
    }

    @Override
    public void getCityData() {
        mModel.getCityData(new CommonContact.CommonCallback() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                CityRawBean cityRawBean = gson.fromJson(s, CityRawBean.class);
                if(!cityRawBean.getHeWeather5().get(0).getStatus().equals("ok")) mActivity.showToast("无搜索结果");
                else {
//                    for(int i = 0; i < cityBean.getResults().size(); i++)
                    cityBean = CityBean.getChange(cityRawBean);
                    for(int i = 0; i < cityBean.getCityBasicBeans().size(); i++)
                        mActivity.setAdapter(cityBean.getCityBasicBeans().get(i));
                    mActivity.notifyAdapter();
                }
            }
        });
    }

    @Override
    public void testCity() {
        mModel.testCity(new CommonContact.CommonCallback() {
            @Override
            public void onResponse(String s) {
                if(s.equals("true")) {
                    mActivity.returnMainPage(mModel.getCityId());
                } else {
                    mActivity.showToast("该城市没有具体天气数据");
                }
            }
        });
    }
}
