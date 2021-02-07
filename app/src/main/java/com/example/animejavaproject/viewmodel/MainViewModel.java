package com.example.animejavaproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.animejavaproject.model.AnimeTopResponse;
import com.example.animejavaproject.model.Top;
import com.example.animejavaproject.repo.AnimeTopRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private static final String PAGE_NUM_DATA_KEY = "PAGE_NUM_DATA_KEY";

    private MutableLiveData<List<Top>> _tops = new MutableLiveData<>();

    public LiveData<List<Top>> getTops() { return _tops; }


    private SharedPreferences sharedPref = getApplication().getSharedPreferences("MY_APP_PREF", Context.MODE_PRIVATE);

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public String getPageData() { return sharedPref.getString(PAGE_NUM_DATA_KEY, "1"); }
    public void savePageData(String data) {
        sharedPref.edit().putString(PAGE_NUM_DATA_KEY, data).apply();
    }
    public void getTopResponse(int page) {
        AnimeTopRepo.getInstance().getTopResponse(page).enqueue(
                new Callback<AnimeTopResponse>() {
                    @Override
                    public void onResponse(Call<AnimeTopResponse> call, Response<AnimeTopResponse> response) {
                        //list of AnimeTop
                        _tops.setValue(response.body().getTop());
                    }

                    @Override
                    public void onFailure(Call<AnimeTopResponse> call, Throwable t) {
                        Log.i("INFO:", "onFailure: " + t.toString());
                    }
                }
        );
    }
}
