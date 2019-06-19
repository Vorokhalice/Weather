package com.example.weather;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public enum Repository {
    REPOSITORY;
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;
    private static final String workerTag = "updateWorkerTag";
    public static void initialize (Context context) {
        REPOSITORY.localDataSource = new LocalDataSource(context);
        REPOSITORY.remoteDataSource = new RemoteDataSource();
        REPOSITORY.scheduleUpdate();
    }

    public LiveData<WeatherEntity> getWeatherData() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                WeatherDay weatherDay = remoteDataSource.getWeatherDay();
                localDataSource.storeWeatherForDay(weatherDay);
            }

        });
        return localDataSource.getWeatherForDay();
    }
    public LiveData<List<ForecastEntity>> getWeatherDataWeek() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                WeatherForecast weatherForecast = remoteDataSource.getWeatherWeek();
                localDataSource.storeWeatherForWeek(weatherForecast);
            }

        });
        return localDataSource.getWeatherForWeek();
    }
    public void scheduleUpdate() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(SyncWorker.class, 1, TimeUnit.HOURS)
                .build();
        WorkManager.getInstance().enqueueUniquePeriodicWork(workerTag, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
    }
}

