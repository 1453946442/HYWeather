package com.hx.weather.hyweather.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hx.weather.hyweather.R;


/**
 * Created by win10 on 2017/9/11.
 */

public class SearchHolder extends RecyclerView.ViewHolder{
    public TextView search_name, search_path;

    public SearchHolder(View itemView) {
        super(itemView);
        search_name = (TextView) itemView.findViewById(R.id.search_name_item);
        search_path = (TextView) itemView.findViewById(R.id.search_path_item);
    }
}
