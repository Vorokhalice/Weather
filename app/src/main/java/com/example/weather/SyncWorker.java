package com.example.weather;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SyncWorker extends Worker {
    public SyncWorker(Context context, WorkerParameters params) {
        super(context, params);
    }
    @NonNull
    @Override
    public Result doWork() {
        Repository.REPOSITORY.getWeatherData();
        Repository.REPOSITORY.getWeatherDataWeek();
        return Result.success();
    }
}

