package com.example.weather.data.database;

import com.example.weather.data.database.entities.*;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherEntity.class, ForecastEntity.class}, version = 1, exportSchema = false)
public abstract class WeatherDB extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}