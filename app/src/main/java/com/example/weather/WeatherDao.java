package com.example.weather;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WeatherDao {
    @Insert
    public void insertWeather(WeatherEntity weatherEntity);
    @Query("SELECT * FROM weather_forecast")
    public LiveData<WeatherEntity> getWeatherForecast();
    @Insert
    public void insertForecast(List<ForecastEntity> forecastEntity);
    @Query("SELECT * FROM forecast")
    public LiveData<List<ForecastEntity>> getForecast();
    @Query("DELETE FROM weather_forecast")
    public void clean();
    @Query("DELETE FROM forecast")
    public void delete();

}
