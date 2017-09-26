package com.hx.weather.hyweather.bean;

import com.hx.weather.hyweather.rawbean.WeatherRawBean;

/**
 * Created by win10 on 2017/9/6.
 */

public class SuggestionWeatherBean extends CommonBean {
    private String air_brf, air_txt;
    private String comf_brf, comf_txt;
    private String cw_brf, cw_txt;
    private String drsg_brf, drsg_txt;
    private String flu_brf, flu_txt;
    private String sport_brf, sport_txt;
    private String trav_brf, trav_txt;
    private String uv_brf, uv_txt;

    public String getAir_brf() {
        return air_brf;
    }

    public void setAir_brf(String air_brf) {
        this.air_brf = air_brf;
    }

    public String getAir_txt() {
        return air_txt;
    }

    public void setAir_txt(String air_txt) {
        this.air_txt = air_txt;
    }

    public String getComf_brf() {
        return comf_brf;
    }

    public void setComf_brf(String comf_brf) {
        this.comf_brf = comf_brf;
    }

    public String getComf_txt() {
        return comf_txt;
    }

    public void setComf_txt(String comf_txt) {
        this.comf_txt = comf_txt;
    }

    public String getCw_brf() {
        return cw_brf;
    }

    public void setCw_brf(String cw_brf) {
        this.cw_brf = cw_brf;
    }

    public String getCw_txt() {
        return cw_txt;
    }

    public void setCw_txt(String cw_txt) {
        this.cw_txt = cw_txt;
    }

    public String getDrsg_brf() {
        return drsg_brf;
    }

    public void setDrsg_brf(String drsg_brf) {
        this.drsg_brf = drsg_brf;
    }

    public String getDrsg_txt() {
        return drsg_txt;
    }

    public void setDrsg_txt(String drsg_txt) {
        this.drsg_txt = drsg_txt;
    }

    public String getFlu_brf() {
        return flu_brf;
    }

    public void setFlu_brf(String flu_brf) {
        this.flu_brf = flu_brf;
    }

    public String getFlu_txt() {
        return flu_txt;
    }

    public void setFlu_txt(String flu_txt) {
        this.flu_txt = flu_txt;
    }

    public String getSport_brf() {
        return sport_brf;
    }

    public void setSport_brf(String sport_brf) {
        this.sport_brf = sport_brf;
    }

    public String getSport_txt() {
        return sport_txt;
    }

    public void setSport_txt(String sport_txt) {
        this.sport_txt = sport_txt;
    }

    public String getTrav_brf() {
        return trav_brf;
    }

    public void setTrav_brf(String trav_brf) {
        this.trav_brf = trav_brf;
    }

    public String getTrav_txt() {
        return trav_txt;
    }

    public void setTrav_txt(String trav_txt) {
        this.trav_txt = trav_txt;
    }

    public String getUv_brf() {
        return uv_brf;
    }

    public void setUv_brf(String uv_brf) {
        this.uv_brf = uv_brf;
    }

    public String getUv_txt() {
        return uv_txt;
    }

    public void setUv_txt(String uv_txt) {
        this.uv_txt = uv_txt;
    }

    public static SuggestionWeatherBean getChange(WeatherRawBean weatherRawBean) {
        SuggestionWeatherBean suggestionWeatherBean = new SuggestionWeatherBean();
        WeatherRawBean.HeWeather5Bean.SuggestionBean suggestionBean = weatherRawBean.getHeWeather5().get(0).getSuggestion();

        suggestionWeatherBean.air_brf = suggestionBean.getAir().getBrf();
        suggestionWeatherBean.air_txt = suggestionBean.getAir().getTxt();

        suggestionWeatherBean.comf_brf = suggestionBean.getComf().getBrf();
        suggestionWeatherBean.comf_txt = suggestionBean.getComf().getTxt();

        suggestionWeatherBean.cw_brf = suggestionBean.getCw().getBrf();
        suggestionWeatherBean.cw_txt = suggestionBean.getCw().getTxt();

        suggestionWeatherBean.drsg_brf = suggestionBean.getDrsg().getBrf();
        suggestionWeatherBean.drsg_txt = suggestionBean.getDrsg().getTxt();

        suggestionWeatherBean.flu_brf = suggestionBean.getFlu().getBrf();
        suggestionWeatherBean.flu_txt = suggestionBean.getFlu().getTxt();

        suggestionWeatherBean.sport_brf = suggestionBean.getSport().getBrf();
        suggestionWeatherBean.sport_txt = suggestionBean.getSport().getTxt();

        suggestionWeatherBean.trav_brf = suggestionBean.getTrav().getBrf();
        suggestionWeatherBean.trav_txt = suggestionBean.getTrav().getTxt();

        suggestionWeatherBean.uv_brf = suggestionBean.getUv().getBrf();
        suggestionWeatherBean.uv_txt = suggestionBean.getUv().getTxt();

        return suggestionWeatherBean;
    }
}
