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

import com.hx.weather.hyweather.R;
import com.hx.weather.hyweather.view.recyclerView.WeatherAdapter;
import com.hx.weather.hyweather.contact.WeatherContact;
import com.hx.weather.hyweather.bean.CommonBean;
import com.hx.weather.hyweather.bean.NowWeatherBean;
import com.hx.weather.hyweather.presenter.WeatherPresenter;
import com.hx.weather.hyweather.util.SPUtil;

public class MainActivity extends AppCompatActivity implements WeatherContact.WeatherView, View.OnClickListener {
    private WeatherAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView nowCity;
    private ImageView imageView;
    private final int REQUEST_CITY_CODE = 1;
    private final int RESULT_FIRST_CODE = 101;
    private String cityId;
    private WeatherPresenter weatherPresenter;
    private boolean flag = false;

    private final String TAG = "MainActivity";

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

        SPUtil.getInstance().clear();

//        cityId = "shenzhen";
        cityId = "CN101280601";   //深圳id
        weatherPresenter = new WeatherPresenter();
        weatherPresenter.attachView(this);
        weatherPresenter.setCityId(cityId);
        weatherPresenter.operateCityId(cityId);
        weatherPresenter.getData();
        flag = true;
//        weatherPresenter.OperationCityId(cityId);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter.clearList();
                weatherPresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            cityId = bundle.getString("city");
            weatherPresenter.setCityId(cityId);
            weatherPresenter.operateCityId(cityId);
            myAdapter.clearList();
            weatherPresenter.getData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CITY_CODE) {
            if(resultCode == RESULT_FIRST_CODE) {
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
                intent.putExtra("list", SPUtil.getInstance().getString("list"));
                Log.v(TAG, "list: " + SPUtil.getString("list"));
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
