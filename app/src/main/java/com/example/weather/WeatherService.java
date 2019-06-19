package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService{
    @GET("/data/2.5/weather")
    Call<WeatherDay> getWeatherByCityName(@Query("q")String city, @Query("appId") String appID, @Query("units") String units);
    @GET("/data/2.5/forecast")
    Call<WeatherForecast> getWeatherForWeek(@Query("q")String city, @Query("appId") String appID, @Query("units") String units);
}