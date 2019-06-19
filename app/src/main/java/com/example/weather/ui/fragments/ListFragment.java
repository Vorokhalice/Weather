package com.example.weather.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weather.data.database.entities.ForecastEntity;
import com.example.weather.viewModels.MainViewModelForecast;
import com.example.weather.R;
import com.example.weather.data.Repository;
import com.example.weather.ui.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment implements Observer<List<ForecastEntity>> {
    private LiveData<List<ForecastEntity>> forecastData;
    private RecyclerView recyclerView;
    private Repository repository;
    @Override
    public void onChanged(List<ForecastEntity> forecastEntities) {
        ((WeatherAdapter)recyclerView.getAdapter()).changeData(forecastEntities);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        MainViewModelForecast mainViewModel = ViewModelProviders.of(this).get(MainViewModelForecast.class);
        repository = Repository.REPOSITORY;
        mainViewModel.loadDataWeek(repository);
        forecastData = mainViewModel.getForecastData();
        forecastData.observe(this, this);
        recyclerView = view.findViewById(R.id.rec_list);
        LinearLayoutManager linearManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(new WeatherAdapter(new ArrayList<ForecastEntity>()));
        return view;
    }
}
