package com.hx.weather.hyweather.bean;

import com.hx.weather.hyweather.rawbean.WeatherRawBean;

/**
 * Created by win10 on 2017/9/6.
 */

public class NowWeatherBean extends CommonBean {
    private String cityName;
    private String cityId;
    private String temperature;
    private String image;
    private String txt;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public static NowWeatherBean getChange(WeatherRawBean weatherRawBean) {
        NowWeatherBean nowWeatherBean = new NowWeatherBean();
        nowWeatherBean.isChecked = false;
        nowWeatherBean.cityName = weatherRawBean.getHeWeather5().get(0).getBasic().getCity();
        nowWeatherBean.cityId = weatherRawBean.getHeWeather5().get(0).getBasic().getId();
        nowWeatherBean.image = weatherRawBean.getHeWeather5().get(0).getNow().getCond().getCode();
        nowWeatherBean.temperature = weatherRawBean.getHeWeather5().get(0).getNow().getTmp();
        nowWeatherBean.txt = weatherRawBean.getHeWeather5().get(0).getNow().getCond().getTxt();
        return nowWeatherBean;
    }
}
