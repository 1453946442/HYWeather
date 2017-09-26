package com.hx.weather.hyweather.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.weather.hyweather.R;

/**
 * Created by win10 on 2017/9/6.
 */

public class NowHolder extends RecyclerView.ViewHolder {
    public TextView now_temperature, now_weather, now_high_low;
    public ImageView now_image;

    public NowHolder(View itemView) {
        super(itemView);
        now_temperature = (TextView) itemView.findViewById(R.id.now_temperature);
        now_weather = (TextView) itemView.findViewById(R.id.now_weather);
//        now_high_low = (TextView) itemView.findViewById(R.id.now_high_low);
        now_image = (ImageView) itemView.findViewById(R.id.now_image);
    }
}
