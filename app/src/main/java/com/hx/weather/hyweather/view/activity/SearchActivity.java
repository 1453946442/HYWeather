package com.hx.weather.hyweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hx.weather.hyweather.R;
import com.hx.weather.hyweather.view.recyclerView.WeatherAdapter;
import com.hx.weather.hyweather.contact.SearchContact;
import com.hx.weather.hyweather.bean.CommonBean;
import com.hx.weather.hyweather.presenter.SearchPresenter;
import com.hx.weather.hyweather.util.HanyuUtil;

import static android.view.View.VISIBLE;

/**
 * Created by win10 on 2017/9/11.
 */

public class SearchActivity extends AppCompatActivity implements SearchContact.SearchView {
    private RecyclerView search_RecyclerView;
    private WeatherAdapter mAdapter;
    private Button btn_search;
    private EditText edit_input;
    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_RecyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
//        search_RecyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL, 3, R.color.colorAccent));
        search_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WeatherAdapter(this);

        btn_search = (Button) findViewById(R.id.btn_search);
        edit_input = (EditText) findViewById(R.id.search_input);

        searchPresenter = new SearchPresenter();
        searchPresenter.attachView(this);

        mAdapter.setCallback(new WeatherAdapter.Callback() {
            @Override
            public void onItemClick(final String cityId) {
                searchPresenter.setCityId(cityId);
                searchPresenter.testCity();
//                returnMainPage(cityId);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_city = edit_input.getText().toString();
                if(!edit_city.equals("")) {
                    edit_city = HanyuUtil.getInstance().getStringPinYin(edit_city);
                    search_RecyclerView.setVisibility(VISIBLE);
                    mAdapter.clearList();

                    searchPresenter.setCityId(edit_city);
                    searchPresenter.getCityData();
                }
            }
        });
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAdapter(CommonBean commonBean) {
        mAdapter.addWeatherList(commonBean);
    }

    @Override
    public void notifyAdapter() {
        search_RecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void returnMainPage(String cityId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city", cityId);
        startActivity(intent);
//        setResult(RESULT_SEARCH_CODE, intent);
//        finish();
    }
}
