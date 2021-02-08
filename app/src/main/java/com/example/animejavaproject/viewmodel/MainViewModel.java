package com.example.animejavaproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.animejavaproject.model.AnimeTopResponse;
import com.example.animejavaproject.model.Top;
import com.example.animejavaproject.repo.AnimeTopRepo;
import com.example.animejavaproject.repo.AnimeTopService;
import com.example.animejavaproject.repo.RetroFitInstance;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private void handleResults(AnimeTopResponse animeTopResponse) {
        if(animeTopResponse != null && animeTopResponse.getTop().size() != 0) {
            _tops.setValue(animeTopResponse.getTop());
        } else {
            Toast.makeText(this.getApplication().getApplicationContext(), "NP RESULTS FOUND", Toast.LENGTH_LONG).show();
        }
    }
    private void handleError(Throwable t) {
        Toast.makeText(this.getApplication().getApplicationContext(), "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
    public void getTopResponse(int page) {
        AnimeTopRepo.getInstance().getTopResponse(page).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .map(result -> result).subscribe(this::handleResults, this::handleError);

//        AnimeTopRepo.getInstance().getTopResponse(page).enqueue(
//                new Callback<AnimeTopResponse>() {
//                    @Override
//                    public void onResponse(Call<AnimeTopResponse> call, Response<AnimeTopResponse> response) {
//                        //list of AnimeTop
//                        _tops.setValue(response.body().getTop());
//                    }
//
//                    @Override
//                    public void onFailure(Call<AnimeTopResponse> call, Throwable t) {
//                        Log.i("INFO:", "onFailure: " + t.toString());
//                    }
//                }
//        );
    }
}
