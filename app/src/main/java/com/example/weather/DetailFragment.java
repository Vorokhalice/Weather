package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailFragment extends Fragment implements Observer<WeatherEntity> {
    private TextView temp;
    private TextView mintemp;
    private TextView maxtemp;
    private ImageView picture;
    private TextView wind;
    private TextView pressure;
    private TextView humidity;
    private TextView clouds;
    private TextView city;
    private TextView deg;
    private LiveData<WeatherEntity> weatherData;
    private Repository repository;
    @Override
    public void onChanged(WeatherEntity weatherEntities) {
        if (weatherEntities != null) {
            mintemp.setText(String.valueOf(Math.round(weatherEntities.tempMin)) + "°C");
            maxtemp.setText(String.valueOf(Math.round(weatherEntities.tempMax)) + "°C");
            temp.setText(String.valueOf(Math.round(weatherEntities.temperature)) + "°C");
            wind.setText(String.valueOf(Math.round(weatherEntities.w)) + " m/s");
            pressure.setText(String.valueOf(Math.round(weatherEntities.pres)) + " hpa");
            humidity.setText(String.valueOf(Math.round(weatherEntities.hum)) + "%");
            clouds.setText(String.valueOf(weatherEntities.descr));
            city.setText(weatherEntities.town);
            double directionInDegrees = weatherEntities.degrees;
            String cardinalDirection = null;
            if ((directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                    (directionInDegrees >= 0) && (directionInDegrees <= 11.25)) {
                cardinalDirection = "N";
            } else if ((directionInDegrees >= 11.25) && (directionInDegrees <= 33.75)) {
                cardinalDirection = "NNE";
            } else if ((directionInDegrees >= 33.75) && (directionInDegrees <= 56.25)) {
                cardinalDirection = "NE";
            } else if ((directionInDegrees >= 56.25) && (directionInDegrees <= 78.75)) {
                cardinalDirection = "ENE";
            } else if ((directionInDegrees >= 78.75) && (directionInDegrees <= 101.25)) {
                cardinalDirection = "E";
            } else if ((directionInDegrees >= 101.25) && (directionInDegrees <= 123.75)) {
                cardinalDirection = "ESE";
            } else if ((directionInDegrees >= 123.75) && (directionInDegrees <= 146.25)) {
                cardinalDirection = "SE";
            } else if ((directionInDegrees >= 146.25) && (directionInDegrees <= 168.75)) {
                cardinalDirection = "SSE";
            } else if ((directionInDegrees >= 168.75) && (directionInDegrees <= 191.25)) {
                cardinalDirection = "S";
            } else if ((directionInDegrees >= 191.25) && (directionInDegrees <= 213.75)) {
                cardinalDirection = "SSW";
            } else if ((directionInDegrees >= 213.75) && (directionInDegrees <= 236.25)) {
                cardinalDirection = "SW";
            } else if ((directionInDegrees >= 236.25) && (directionInDegrees <= 258.75)) {
                cardinalDirection = "WSW";
            } else if ((directionInDegrees >= 258.75) && (directionInDegrees <= 281.25)) {
                cardinalDirection = "W";
            } else if ((directionInDegrees >= 281.25) && (directionInDegrees <= 303.75)) {
                cardinalDirection = "WNW";
            } else if ((directionInDegrees >= 303.75) && (directionInDegrees <= 326.25)) {
                cardinalDirection = "NW";
            } else if ((directionInDegrees >= 326.25) && (directionInDegrees <= 348.75)) {
                cardinalDirection = "NNW";
            } else {
                cardinalDirection = "?";
            }
            deg.setText(String.valueOf(cardinalDirection));
            String pict = String.valueOf(weatherEntities.icon);
            if (pict.equals("01d")) {
                picture.setImageResource(R.drawable.sunny);
            } else if (pict.equals("01n")) {
                picture.setImageResource(R.drawable.clear);
            } else if (pict.equals("02d")) {
                picture.setImageResource(R.drawable.cloudy);
            } else if (pict.equals("02n")) {
                picture.setImageResource(R.drawable.cloudnight);
            } else if (pict.equals("03d")) {
                picture.setImageResource(R.drawable.fullclouds);
            } else if (pict.equals("03n")) {
                picture.setImageResource(R.drawable.fullcloudnight);
            } else if (pict.equals("04d")) {
                picture.setImageResource(R.drawable.broken);
            } else if (pict.equals("04n")) {
                picture.setImageResource(R.drawable.brokencloudnight);
            } else if (pict.equals("09d")) {
                picture.setImageResource(R.drawable.rain);
            } else if (pict.equals("09n")) {
                picture.setImageResource(R.drawable.nightrain);
            } else if (pict.equals("10d")) {
                picture.setImageResource(R.drawable.sunrain);
            } else if (pict.equals("10n")) {
                picture.setImageResource(R.drawable.rainmoon);
            } else if (pict.equals("11d")) {
                picture.setImageResource(R.drawable.thunder);
            } else if (pict.equals("11n")) {
                picture.setImageResource(R.drawable.nightthunder);
            } else if (pict.equals("13d")) {
                picture.setImageResource(R.drawable.snow);
            } else if (pict.equals("13n")) {
                picture.setImageResource(R.drawable.snownight);
            } else if (pict.equals("50d") || pict.equals("50n")) {
                picture.setImageResource(R.drawable.fog);
            } else picture.setImageResource(R.drawable.sunny);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);
        MainViewModelWeather mainViewModel = ViewModelProviders.of(this).get(MainViewModelWeather.class);
        repository = Repository.REPOSITORY;
        mainViewModel.loadData(repository);
        weatherData = mainViewModel.getWeatherData();
        wind = view.findViewById(R.id.wind);
        wind = view.findViewById(R.id.wind);
        temp = view.findViewById(R.id.temp);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        deg = view.findViewById(R.id.deg);
        city = view.findViewById(R.id.city);
        maxtemp = view.findViewById(R.id.maxtemp);
        mintemp = view.findViewById(R.id.mintemp);
        weatherData.observe(this, this);
        clouds = view.findViewById(R.id.clouds);
        picture = view.findViewById(R.id.picture);
        return view;
    }
}
