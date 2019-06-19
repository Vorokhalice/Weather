package com.example.weather;
import android.app.Application;

import com.example.weather.data.Repository;

public class WeatherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.initialize(this);
    }

}
