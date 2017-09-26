package com.hx.weather.hyweather.bean;

import com.hx.weather.hyweather.rawbean.CityRawBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win10 on 2017/9/25.
 */

public class CityBean {
    private List<CityBasicBean> cityBasicBeans;

    public List<CityBasicBean> getCityBasicBeans() {
        return cityBasicBeans;
    }

    public void setCityBasicBeans(List<CityBasicBean> cityBasicBeans) {
        this.cityBasicBeans = cityBasicBeans;
    }

    public static class CityBasicBean extends CommonBean {
        private String status;
        private String cityName;
        private String cityId;
        private String province;
        private String country;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }


    public static CityBean getChange(CityRawBean cityRawBean) {
        CityBean cityBean = new CityBean();
        List<CityBasicBean> cityBasicBeen = new ArrayList<>();
        for(int i = 0; i < cityRawBean.getHeWeather5().size(); i++) {
            CityBasicBean cityBasicBean = new CityBasicBean();
            cityBasicBean.status = cityRawBean.getHeWeather5().get(i).getStatus();
            CityRawBean.HeWeather5Bean.BasicBean basicBean = cityRawBean.getHeWeather5().get(i).getBasic();
            cityBasicBean.cityName = basicBean.getCity();
            cityBasicBean.country = basicBean.getCnty();
            cityBasicBean.cityId = basicBean.getId();
            cityBasicBean.province = basicBean.getProv();
            cityBasicBeen.add(cityBasicBean);
        }
        cityBean.setCityBasicBeans(cityBasicBeen);
        return cityBean;
    }


}
