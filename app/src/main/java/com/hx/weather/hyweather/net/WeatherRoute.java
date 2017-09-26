package com.hx.weather.hyweather.net;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by win10 on 2017/9/5.
 */

public class WeatherRoute {
//    public final static String NOW_WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json?key=uwvu2gbsmqcirqg3&language=zh-Hans&unit=c&location=";
//    public final static String DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json?key=uwvu2gbsmqcirqg3&language=zh-Hans&location=";
//    public final static String SUGGESTION_WEATHER_URL = "https://api.seniverse.com/v3/life/suggestion.json?key=uwvu2gbsmqcirqg3&language=zh-Hans&location=";
//
//    public final static String SEARCH_CITY_BYID = "https://api.seniverse.com/v3/location/search.json?key=uwvu2gbsmqcirqg3&language=zh-Hans&q=";
//    public final static String IMAGE_WEATHER_URL = "https://s1.sencdn.com/web/icons/3d_50/";
//
//    public final static String LANGUAGE = "zh-Hans";
//    public final static String SECRET_WEATHER_KEY = "uwvu2gbsmqcirqg3";
//    public final static String USER_WEATHER_ID = "U6CCEB818C";

    public final static String COMMON_WEATHER_URL = "https://free-api.heweather.com/v5/";

    public final static String WEATHER_BYCITY = "https://free-api.heweather.com/v5/weather?key=21e0a11de1d24deb87ab3165d6ed8d2e&city=";

    public final static String SEARCH_BYCITY = "https://api.heweather.com/v5/search?key=21e0a11de1d24deb87ab3165d6ed8d2e&city=";

    public final static String IMAGE_URL = "https://cdn.heweather.com/cond_icon/";

    private final static String MYKEY = "21e0a11de1d24deb87ab3165d6ed8d2e";

}
