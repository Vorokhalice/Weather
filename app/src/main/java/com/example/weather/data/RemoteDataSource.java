package com.example.weather.data;

import android.util.Log;


import com.example.weather.data.pojo.WeatherDay;
import com.example.weather.data.pojo.WeatherForecast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSource {
    private static String API_KEY = "c89704617764eb6d325c853555bdb333";
    private WeatherService weatherService;
    public RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);
    }

    public WeatherDay getWeatherDay(){
        Call<WeatherDay> call = weatherService.getWeatherByCityName("Kiev", API_KEY, "metric");
        try {
            Response<WeatherDay> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        }catch (IOException ioex) {
            Log.e("Remote", "dfsdfsdfs ");
        }
        return null;
    }
    public WeatherForecast getWeatherWeek() {
        Call<WeatherForecast> call = weatherService.getWeatherForWeek(
                "Kiev", API_KEY, "metric");
        try {
            Response<WeatherForecast> response = call.execute();
            if (response.isSuccessful()) {
                Log.e("Remote", "успех");
                return response.body();
            }
            else {
                return null;
            }
        } catch (IOException e) {
            Log.e("Remote", "ERROR" + e);
            return null;
        }
    }
}
