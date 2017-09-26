package com.hx.weather.hyweather.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.weather.hyweather.R;

/**
 * Created by win10 on 2017/9/6.
 */

public class DailyHolder extends RecyclerView.ViewHolder {
    public TextView daily_date, daily_weather, daily_high_low;
    public ImageView daily_image;

    public DailyHolder(View itemView) {
        super(itemView);
        daily_date = (TextView) itemView.findViewById(R.id.daily_date);
        daily_weather = (TextView) itemView.findViewById(R.id.daily_weather);
        daily_high_low = (TextView) itemView.findViewById(R.id.daily_high_low);
        daily_image = (ImageView) itemView.findViewById(R.id.daily_image);
    }
}
