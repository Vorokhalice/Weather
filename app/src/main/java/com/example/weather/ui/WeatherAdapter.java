package com.example.weather.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.database.entities.ForecastEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<ForecastEntity> weatherDays;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());

    public WeatherAdapter(List<ForecastEntity> weatherDays) {
        this.weatherDays = weatherDays;
    }

    public void changeData(List<ForecastEntity> weatherDays) {
        this.weatherDays = weatherDays;
        notifyDataSetChanged();
    }

    class WeatherHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView text1;
        private TextView text2;
        private TextView text3;

        public WeatherHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            text1 = view.findViewById(R.id.text1);
            text2 = view.findViewById(R.id.text2);
            text3 = view.findViewById(R.id.text3);
        }

        public String convertTime(long date) {
            return simpleDateFormat.format(new Date(date*1000));
        }

        public void setContent(ForecastEntity weatherDay) {
            String pict = weatherDay.ic;
            if (pict.equals("01d")) {
                icon.setImageResource(R.drawable.sunny);
            }
            else if (pict.equals("01n")) {
                icon.setImageResource(R.drawable.clear);
            }
            else if (pict.equals("02d")) {
                icon.setImageResource(R.drawable.cloudy);
            }
            else if (pict.equals("02n")) {
                icon.setImageResource(R.drawable.cloudnight);
            }
            else if (pict.equals("03d")) {
                icon.setImageResource(R.drawable.fullclouds);
            }
            else if (pict.equals("03n")) {
                icon.setImageResource(R.drawable.fullcloudnight);
            }
            else if (pict.equals("04d")) {
                icon.setImageResource(R.drawable.broken);
            }
            else if (pict.equals("04n")) {
                icon.setImageResource(R.drawable.brokencloudnight);
            }
            else if (pict.equals("09d")) {
                icon.setImageResource(R.drawable.rain);
            }
            else if (pict.equals("09n")) {
                icon.setImageResource(R.drawable.nightrain);
            }
            else if (pict.equals("10d")) {
                icon.setImageResource(R.drawable.sunrain);
            }
            else if (pict.equals("10n")) {
                icon.setImageResource(R.drawable.rainmoon);
            }
            else if (pict.equals("11d")) {
                icon.setImageResource(R.drawable.thunder);
            }
            else if (pict.equals("11n")) {
                icon.setImageResource(R.drawable.nightthunder);
            }
            else if (pict.equals("13d")) {
                icon.setImageResource(R.drawable.snow);
            }
            else if (pict.equals("13n")) {
                icon.setImageResource(R.drawable.snownight);
            }
            else if (pict.equals("50d")||pict.equals("50n")) {
                icon.setImageResource(R.drawable.fog);
            }
            else icon.setImageResource(R.drawable.sunny);
            text1.setText(convertTime(weatherDay.data));
            text3.setText(String.valueOf(Math.round(weatherDay.maxtemp)+"°C/"+String.valueOf(Math.round(weatherDay.mintemp)))+"°C");
            text2.setText(String.valueOf(weatherDay.description));
            Log.e("adapter","Exception");
        }
    }
    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_item, viewGroup, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder weatherHolder, int i) {
        weatherHolder.setContent(weatherDays.get(i));
    }

    @Override
    public int getItemCount() {
        return weatherDays.size();
    }
}
