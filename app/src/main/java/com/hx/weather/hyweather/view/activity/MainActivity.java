package com.hx.weather.hyweather.view.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.hx.weather.hyweather.R;
import com.hx.weather.hyweather.bd.MyLocationListener;
import com.hx.weather.hyweather.view.recyclerView.WeatherAdapter;
import com.hx.weather.hyweather.contact.WeatherContact;
import com.hx.weather.hyweather.bean.CommonBean;
import com.hx.weather.hyweather.bean.NowWeatherBean;
import com.hx.weather.hyweather.presenter.WeatherPresenter;
import com.hx.weather.hyweather.util.SPUtil;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.Poi;

public class MainActivity extends AppCompatActivity implements WeatherContact.WeatherView, View.OnClickListener {
    private WeatherAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView nowCity;
    private ImageView imageView;
    private final int REQUEST_CITY_CODE = 1;
    private final int RESULT_CITY_CODE = 101;
    private String cityId;
    private WeatherPresenter weatherPresenter;
    private boolean flag = false;

    private final String TAG = "MainActivity";

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        SPUtil.init(this);

        nowCity = (TextView) findViewById(R.id.nowCity);
        imageView = (ImageView) findViewById(R.id.cityid);
        imageView.setOnClickListener(this);

        myAdapter = new WeatherAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.weather_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        initLocations();

        SPUtil.getInstance().clear();

//        cityId = "shenzhen";
        cityId = "CN101280601";   //深圳id
        weatherPresenter = new WeatherPresenter();
        weatherPresenter.attachView(this);
        weatherPresenter.setCityId(cityId);
        weatherPresenter.addCityId(cityId);
        weatherPresenter.getData();
        flag = true;
        weatherPresenter.addCityId(cityId);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter.clearList();
                weatherPresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void initLocations() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        //注册监听函数
        initLocation();
        mLocationClient.start();
//        myListener.getCity();
        Log.v(TAG, "cityId: " + cityId);
    }

    private void initLocation(){

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            cityId = bundle.getString("city");
            weatherPresenter.setCityId(cityId);
            weatherPresenter.addCityId(cityId);
            myAdapter.clearList();
            weatherPresenter.getData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CITY_CODE) {
            if(resultCode == RESULT_CITY_CODE) {
                cityId = data.getStringExtra("city");
                myAdapter.clearList();
                weatherPresenter.setCityId(cityId);
                weatherPresenter.getData();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cityid:
                Intent intent = new Intent(this, ShowCityListActivity.class);
                startActivityForResult(intent, REQUEST_CITY_CODE);
                break;
            default:break;
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAdapter(CommonBean commonBean) {
        if(commonBean instanceof NowWeatherBean) {
            NowWeatherBean nowWeatherBean = (NowWeatherBean) commonBean;
            nowCity.setText(nowWeatherBean.getCityName());
        }
        myAdapter.addWeatherList(commonBean);
    }

    @Override
    public void notifyAdapter() {
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
