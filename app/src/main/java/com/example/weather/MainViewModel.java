package com.example.weather;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MainViewModel(){}
    private Repository repository;
    LiveData<WeatherEntity> weatherData;
    LiveData<List<ForecastEntity>> forecastData;
    public void loadData(Repository repository) {
        if (weatherData == null) {
            this.repository = repository;
            weatherData = repository.getWeatherData();
            //repository.scheduleUpdate();
        }
    }

    public LiveData<WeatherEntity> getWeatherData(){
        return weatherData;
    }

    public void loadDataWeek(Repository repository) {
        if (forecastData == null) {
            this.repository = repository;
            forecastData = repository.getWeatherDataWeek();
        }
    }

    public LiveData<List<ForecastEntity>> getForecastData() {return forecastData;}
}
