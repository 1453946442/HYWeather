package com.hx.weather.hyweather.view.recyclerView;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.hx.weather.hyweather.R;
import com.hx.weather.hyweather.bean.CityBean;
import com.hx.weather.hyweather.net.WeatherRoute;
import com.hx.weather.hyweather.bean.CommonBean;
import com.hx.weather.hyweather.bean.DailyWeatherBean;
import com.hx.weather.hyweather.bean.NowWeatherBean;
import com.hx.weather.hyweather.bean.SuggestionWeatherBean;
import com.hx.weather.hyweather.holder.CityListHolder;
import com.hx.weather.hyweather.holder.DailyHolder;
import com.hx.weather.hyweather.holder.NowHolder;
import com.hx.weather.hyweather.holder.SearchHolder;
import com.hx.weather.hyweather.holder.SuggestionHolder;
import com.hx.weather.hyweather.view.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by win10 on 2017/9/6.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String WHICH_DAY;
    private Context context;
    private List<CommonBean> weatherList = new ArrayList<>();
    private boolean flag;
//    private List<String> checked = new ArrayList<>();

    private final String TODAY = "今天";
    private final String TOMORROW = "明天";
    private final String AFTERTOMORROW = "后天";

    private final String TAG = "WeatherAdapter";

    public WeatherAdapter(Context context) {
        if(context instanceof MainActivity) {
            flag = true;
        } else {
            flag = false;
        }
        this.context = context;
    }

    public void addWeatherList(CommonBean commonBean) {
        weatherList.add(commonBean);
    }

    public List<CommonBean> getWeatherList() {
        return weatherList;
    }

    public void clearList() {
        if(!weatherList.isEmpty())
            weatherList.clear();
    }

    public boolean isDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour= sdf.format(new Date());
        int k  = Integer.parseInt(hour)  ;
        if ((k >= 0 && k < 6) || (k >= 18 && k < 24)) {
            return false;
        }
        return true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case -2:
                holder = new CityListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_citylist_item, null, false));
                break;
            case -1:
                holder = new NowHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_now, null, false));
                break;
            case 0:
                holder = new DailyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_daily, null, false));
                break;
            case 1:
                holder = new SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_item, null, false));
                break;
            case 2:
                holder = new SuggestionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_suggestion, null, false));
                break;
            default:break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case -2:
                final NowWeatherBean nowWeatherBean = (NowWeatherBean) weatherList.get(position);
                ((CityListHolder) holder).cityTemperature.setText(nowWeatherBean.getTemperature());
                ((CityListHolder) holder).cityName.setText(nowWeatherBean.getCityName());
                Glide.with(context)
                        .load(WeatherRoute.IMAGE_URL + nowWeatherBean.getImage() + ".png")
                        .into(((CityListHolder) holder).image_weather);
                ((CityListHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callback!=null)
                            callback.onItemClick(nowWeatherBean.getCityId());
                    }
                });
                ((CityListHolder) holder).checkBox.setChecked(nowWeatherBean.isChecked());
                ((CityListHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        nowWeatherBean.setChecked(isChecked);
                    }
                });
                break;
            case -1:
                NowWeatherBean nowBean = (NowWeatherBean) weatherList.get(position);
                ((NowHolder) holder).now_temperature.setText(nowBean.getTemperature());
                ((NowHolder) holder).now_weather.setText(nowBean.getTxt());
                Glide.with(context)
                        .load(WeatherRoute.IMAGE_URL + nowBean.getImage() + ".png")
                        .into(((NowHolder) holder).now_image);
                break;
            case 0:
                DailyWeatherBean.DailyBean dailyBean = (DailyWeatherBean.DailyBean) weatherList.get(position);
                switch (position) {
                    case 1: WHICH_DAY = TODAY; break;
                    case 2: WHICH_DAY = TOMORROW; break;
                    case 3: WHICH_DAY = AFTERTOMORROW; break;
                    default: break;
                }
                ((DailyHolder) holder).daily_date.setText(dailyBean.getDate().substring(dailyBean.getDate().length()-5) + " " + WHICH_DAY);
                if(position == 1) {
                    if (isDay()) {
                        ((DailyHolder) holder).daily_weather.setText(dailyBean.getTxt_d());
                        Glide.with(context)
                                .load(WeatherRoute.IMAGE_URL + dailyBean.getCode_d() + ".png")
                                .into(((DailyHolder) holder).daily_image);
                    } else {
                        ((DailyHolder) holder).daily_weather.setText(dailyBean.getTxt_n());
                        Glide.with(context)
                                .load(WeatherRoute.IMAGE_URL + dailyBean.getCode_n() + ".png")
                                .into(((DailyHolder) holder).daily_image);
                    }
                } else {
                    ((DailyHolder) holder).daily_weather.setText(dailyBean.getTxt_d());
                    Glide.with(context)
                            .load(WeatherRoute.IMAGE_URL + dailyBean.getCode_d() + ".png")
                            .into(((DailyHolder) holder).daily_image);
                }
                ((DailyHolder) holder).daily_high_low.setText(dailyBean.getTmp_max() + "/" + dailyBean.getTmp_min());
                break;
            case 1:
                final CityBean.CityBasicBean cityBasicBean = (CityBean.CityBasicBean) weatherList.get(position);
                ((SearchHolder) holder).search_name.setText(cityBasicBean.getCityName());
                ((SearchHolder) holder).search_path.setText(cityBasicBean.getCityName() + "," + cityBasicBean.getProvince() + "," + cityBasicBean.getCountry());
                ((SearchHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callback!=null)
                            callback.onItemClick(cityBasicBean.getCityId());
                    }
                });
                break;
            case 2:
                final SuggestionWeatherBean suggestionBean = (SuggestionWeatherBean) weatherList.get(position);
                ((SuggestionHolder) holder).comfor.setText(suggestionBean.getComf_brf());
                ((SuggestionHolder) holder).car_washing.setText(suggestionBean.getCw_brf());
                ((SuggestionHolder) holder).dressing.setText(suggestionBean.getDrsg_brf());
                ((SuggestionHolder) holder).flu.setText(suggestionBean.getFlu_brf());
                ((SuggestionHolder) holder).sport.setText(suggestionBean.getSport_brf());
                ((SuggestionHolder) holder).travel.setText(suggestionBean.getTrav_brf());
                ((SuggestionHolder) holder).uv.setText(suggestionBean.getUv_brf());
                ((SuggestionHolder) holder).comfor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getComf_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).car_washing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getCw_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).dressing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getDrsg_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).flu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getFlu_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).sport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getSport_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).travel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getTrav_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                ((SuggestionHolder) holder).uv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setMessage(suggestionBean.getUv_txt())
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });

                break;
            default:break;
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(weatherList.get(position) instanceof NowWeatherBean && flag) return -1;
        else if(weatherList.get(position) instanceof DailyWeatherBean.DailyBean) return 0;
        else if(weatherList.get(position) instanceof SuggestionWeatherBean) return 2;
        else if(weatherList.get(position) instanceof NowWeatherBean && !flag) return -2;
        else return 1;
    }

    public interface Callback {
        void onItemClick(String result);
    }
    private Callback callback;
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface DeleteCallback {
        void deleteItem(int id);
    }

    public interface CheckAllCallback {
        void checkAll(List<CommonBean> commonBeanList);
    }
    private CheckAllCallback checkAllCallback;

    public void setCheckAllCallback(CheckAllCallback checkAllCallback) {
        this.checkAllCallback = checkAllCallback;
    }
}
