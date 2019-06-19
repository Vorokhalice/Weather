package com.example.weather.data.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_forecast")
public class WeatherEntity {
    @PrimaryKey
    public Integer key;
    public double tempMin;
    public double tempMax;
    public double temperature;
    public double w;
    public double pres;
    public double hum;
    public String descr;
    public String town;
    public double degrees;
    public String icon;
}
