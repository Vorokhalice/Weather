package com.example.weather;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "forecast")
public class ForecastEntity {
    @PrimaryKey
    public Integer key;
    public double mintemp;
    public double maxtemp;
    public String ic;
    public String description;
    public int data;
}