package com.example.animejavaproject.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.model.AnimeTopResponse;
import com.example.animejavaproject.repo.AnimeTopRepo;
import com.example.animejavaproject.repo.AnimeTopService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeViewModel extends AndroidViewModel {
    private MutableLiveData<AnimeResponse> _anime = new MutableLiveData<>();

    public LiveData<AnimeResponse> getAnime() { return _anime; }

    public AnimeViewModel(@NonNull Application application) {
        super(application);
    }

    private void handleResults(AnimeResponse animeResponse) {
        if(animeResponse != null) {
            _anime.setValue(animeResponse);
        } else {
            Toast.makeText(this.getApplication().getApplicationContext(), "NO RESULTS FOUND", Toast.LENGTH_LONG).show();
        }
    }
    private void handleError(Throwable t) {
        Toast.makeText(this.getApplication().getApplicationContext(), "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
    public void getAnimeResponse(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        AnimeTopService animeTopService = retrofit.create(AnimeTopService.class);
        Observable<AnimeResponse> animeResponseObservable =
                animeTopService.getAnimeResponse(id);
        animeResponseObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .map(result -> result).subscribe(this::handleResults, this::handleError);
//        AnimeTopRepo.getInstance().getAnimeResponse(id).enqueue(new Callback<AnimeResponse>() {
//            @Override
//            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
//                String duration = response.body().getDuration();
//                String rating = response.body().getRating();
//                String synopsis = response.body().getSynopsis();
//                _anime.setValue(new AnimeResponse(duration, rating, synopsis));
//            }
//
//            @Override
//            public void onFailure(Call<AnimeResponse> call, Throwable t) {
//
//            }
//        });
    }
}
