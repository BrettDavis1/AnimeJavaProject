package com.example.animejavaproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.repo.AnimeTopRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeViewModel extends AndroidViewModel {
    private MutableLiveData<AnimeResponse> _anime = new MutableLiveData<>();

    public LiveData<AnimeResponse> getAnime() { return _anime; }

    public AnimeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAnimeResponse(int id) {
        AnimeTopRepo.getInstance().getAnimeResponse(id).enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                String duration = response.body().getDuration();
                String rating = response.body().getRating();
                String synopsis = response.body().getSynopsis();
                _anime.setValue(new AnimeResponse(duration, rating, synopsis));
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {

            }
        });
    }
}
