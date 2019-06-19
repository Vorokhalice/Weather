package com.example.weather;
import android.app.Application;

public class WeatherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.initialize(this);
    }

}
