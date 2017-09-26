package com.hx.weather.hyweather.bean;

import com.hx.weather.hyweather.rawbean.WeatherRawBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win10 on 2017/9/6.
 */

public class DailyWeatherBean {
    private List<DailyBean> dailys;

    public List<DailyBean> getDailys() {
        return dailys;
    }

    public void setDailys(List<DailyBean> dailys) {
        this.dailys = dailys;
    }

    public static class DailyBean extends CommonBean {
        private String txt_d, txt_n;
        private String code_d, code_n;
        private String date;
        private String tmp_max, tmp_min;

        public String getTxt_d() {
            return txt_d;
        }

        public void setTxt_d(String txt_d) {
            this.txt_d = txt_d;
        }

        public String getTxt_n() {
            return txt_n;
        }

        public void setTxt_n(String txt_n) {
            this.txt_n = txt_n;
        }

        public String getCode_d() {
            return code_d;
        }

        public void setCode_d(String code_d) {
            this.code_d = code_d;
        }

        public String getCode_n() {
            return code_n;
        }

        public void setCode_n(String code_n) {
            this.code_n = code_n;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTmp_max() {
            return tmp_max;
        }

        public void setTmp_max(String tmp_max) {
            this.tmp_max = tmp_max;
        }

        public String getTmp_min() {
            return tmp_min;
        }

        public void setTmp_min(String tmp_min) {
            this.tmp_min = tmp_min;
        }
    }

    public static DailyWeatherBean getChange(WeatherRawBean weatherRawBean) {
        DailyWeatherBean dailyWeatherBean = new DailyWeatherBean();
        List<DailyBean> dailyBeen = new ArrayList<>();
        for(int i = 0; i < weatherRawBean.getHeWeather5().get(0).getDaily_forecast().size(); i++) {
            DailyBean dailyBean = new DailyBean();
            WeatherRawBean.HeWeather5Bean.DailyForecastBean dailyForecastBean = weatherRawBean.getHeWeather5().get(0).getDaily_forecast().get(i);

            dailyBean.date = dailyForecastBean.getDate();
            dailyBean.code_d = dailyForecastBean.getCond().getCode_d();
            dailyBean.code_n = dailyForecastBean.getCond().getCode_n();

            dailyBean.txt_d = dailyForecastBean.getCond().getTxt_d();
            dailyBean.txt_n = dailyForecastBean.getCond().getTxt_n();

            dailyBean.tmp_max = dailyForecastBean.getTmp().getMax();
            dailyBean.tmp_min = dailyForecastBean.getTmp().getMin();

            dailyBeen.add(dailyBean);
        }
        dailyWeatherBean.setDailys(dailyBeen);
        return dailyWeatherBean;
    }
}
