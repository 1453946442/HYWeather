package com.hx.weather.hyweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hx.weather.hyweather.R;
import com.hx.weather.hyweather.view.recyclerView.WeatherAdapter;
import com.hx.weather.hyweather.contact.CityListContact;
import com.hx.weather.hyweather.bean.CommonBean;
import com.hx.weather.hyweather.presenter.CityListPresenter;
import com.hx.weather.hyweather.util.SPUtil;


/**
 * Created by win10 on 2017/9/21.
 */

public class ShowCityListActivity extends AppCompatActivity implements View.OnClickListener, CityListContact.CityListView {
    private ImageView delete, checkAll, add;

    private WeatherAdapter weatherAdapter;
    private RecyclerView recyclerView;
    private CityListPresenter cityListPresenter;

    private int RESULT_SEARCH_CODE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcitylist);

        SPUtil.init(this);

        delete = (ImageView) findViewById(R.id.delete);
        checkAll = (ImageView) findViewById(R.id.check_all);
        add = (ImageView) findViewById(R.id.add);

        delete.setOnClickListener(this);
        checkAll.setOnClickListener(this);
        add.setOnClickListener(this);

        weatherAdapter = new WeatherAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.citylist_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityListPresenter = new CityListPresenter();
        cityListPresenter.attachView(this);
        if(getIntent().getExtras() != null) {
            cityListPresenter.getNowData(getIntent().getStringExtra("list").split(","));
        }

        weatherAdapter.setCallback(new WeatherAdapter.Callback() {
            @Override
            public void onItemClick(final String cityId) {
                cityListPresenter.returnCityId(cityId);
            }
        });
    }

    public String[] getCityList() {
        if(SPUtil.getInstance().getString("list").equals("")) {
            return null;
        } else {
            return SPUtil.getInstance().getString("list").split(",");
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAdapter(CommonBean commonBean) {
        weatherAdapter.addWeatherList(commonBean);
    }

    @Override
    public void notifyAdapter() {
        recyclerView.setAdapter(weatherAdapter);
        weatherAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                delete();
                break;
            case R.id.check_all:
                checkAll();
                break;
            case R.id.add:
                add();
                break;
            default:break;
        }
    }

    public void delete() {

    }

    public void checkAll() {
//        weatherAdapter.getCheckAllCallback();
//        weatherAdapter.
    }

    public void add() {
        Intent intent = new Intent(ShowCityListActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void returnMainPage(String cityId) {
        weatherAdapter.clearList();
        Intent intent = new Intent();
        intent.putExtra("city", cityId);
        setResult(RESULT_SEARCH_CODE, intent);
        finish();
    }

//    public interface CheckAllCallBack {
//        void
//    }
}
