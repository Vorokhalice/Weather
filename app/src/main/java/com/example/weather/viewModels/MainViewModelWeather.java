package com.example.weather.viewModels;

import com.example.weather.data.Repository;
import com.example.weather.data.database.entities.WeatherEntity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModelWeather extends ViewModel {
    public MainViewModelWeather(){}
    private Repository repository;
    LiveData<WeatherEntity> weatherData;
    public void loadData(Repository repository) {
        if (weatherData == null) {
            this.repository = repository;
            weatherData = repository.getWeatherData();
        }
    }

    public LiveData<WeatherEntity> getWeatherData(){
        return weatherData;
    }
}
