package com.hx.weather.hyweather.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hx.weather.hyweather.R;

/**
 * Created by win10 on 2017/9/6.
 */

public class SuggestionHolder extends RecyclerView.ViewHolder {
    public TextView car_washing, dressing, flu;
    public TextView sport, travel, uv;

    public SuggestionHolder(View itemView) {
        super(itemView);

        car_washing = (TextView) itemView.findViewById(R.id.car_washing);
        dressing = (TextView) itemView.findViewById(R.id.dressing);
        flu = (TextView) itemView.findViewById(R.id.flu);

        sport = (TextView) itemView.findViewById(R.id.sport);
        travel = (TextView) itemView.findViewById(R.id.travel);
        uv = (TextView) itemView.findViewById(R.id.uv);
    }
}
