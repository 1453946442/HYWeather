package com.hx.weather.hyweather.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.weather.hyweather.R;

/**
 * Created by win10 on 2017/9/21.
 */

public class CityListHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public TextView cityName;
    public ImageView image_weather;
    public TextView cityTemperature;

    public CityListHolder(View itemView) {
        super(itemView);

        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        cityName = (TextView) itemView.findViewById(R.id.cityName);
        image_weather = (ImageView) itemView.findViewById(R.id.image_weather);
        cityTemperature = (TextView) itemView.findViewById(R.id.cityTemperature);
    }
}
