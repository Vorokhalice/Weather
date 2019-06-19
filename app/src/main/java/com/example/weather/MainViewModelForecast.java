package com.example.weather;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModelForecast extends ViewModel {
    public MainViewModelForecast(){}
    private Repository repository;
    LiveData<List<ForecastEntity>> forecastData;
    public void loadDataWeek(Repository repository) {
        if (forecastData == null) {
            this.repository = repository;
            forecastData = repository.getWeatherDataWeek();
        }
    }

    public LiveData<List<ForecastEntity>> getForecastData() {return forecastData;}
}
