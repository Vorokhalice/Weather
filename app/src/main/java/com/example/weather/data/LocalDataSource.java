package com.example.weather.data;

import android.content.Context;
import android.util.Log;

import com.example.weather.data.database.WeatherDB;
import com.example.weather.data.pojo.WeatherDay;
import com.example.weather.data.database.entities.ForecastEntity;
import com.example.weather.data.database.entities.WeatherEntity;
import com.example.weather.data.pojo.WeatherForecast;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class LocalDataSource {
    final WeatherDB db;
    public LocalDataSource(Context context) {
        db = Room.databaseBuilder(context, WeatherDB.class, "weather").build();
    }
    public void storeWeatherForDay(WeatherDay weatherDay){
        if (weatherDay != null) {
            db.weatherDao().clean();
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.tempMin = weatherDay.getMain().getTemp_min();
            weatherEntity.tempMax = weatherDay.getMain().getTemp_max();
            weatherEntity.icon = weatherDay.getWeather().get(0).getIcon();
            weatherEntity.temperature = weatherDay.getMain().getTemp();
            weatherEntity.w = weatherDay.getWind().getSpeed();
            weatherEntity.pres = weatherDay.getMain().getPressure();
            weatherEntity.hum = weatherDay.getMain().getHumidity();
            weatherEntity.descr = weatherDay.getWeather().get(0).getDescription();
            weatherEntity.town = "Kiev";
            weatherEntity.degrees = weatherDay.getWind().getDeg();
            db.weatherDao().insertWeather(weatherEntity);
        }
    }
    public void storeWeatherForWeek(WeatherForecast weatherForecast){
        if (weatherForecast !=null) {
            db.weatherDao().delete();
            Log.e("LocalData"," ");
            List<ForecastEntity> weatherList = new ArrayList<>();
            for (WeatherDay weatherDay : weatherForecast.getWeatherDays()) {
                ForecastEntity forecastEntity = new ForecastEntity();
                forecastEntity.mintemp = weatherDay.getMain().getTemp_min();
                forecastEntity.maxtemp = weatherDay.getMain().getTemp_max();
                forecastEntity.ic = weatherDay.getWeather().get(0).getIcon();
                forecastEntity.description = weatherDay.getWeather().get(0).getDescription();
                forecastEntity.data = weatherDay.getDt();
                weatherList.add(forecastEntity);
            }
            db.weatherDao().insertForecast(weatherList);
        }
    }
    public LiveData<WeatherEntity> getWeatherForDay(){
        return db.weatherDao().getWeatherForecast();
    }
    public LiveData<List<ForecastEntity>> getWeatherForWeek(){
        return db.weatherDao().getForecast();
    }
}

