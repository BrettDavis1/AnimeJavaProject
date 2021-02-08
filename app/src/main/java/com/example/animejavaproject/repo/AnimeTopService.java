package com.example.animejavaproject.repo;

import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.model.AnimeTopResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnimeTopService {
    @GET("top/anime/{page}")
    Observable<AnimeTopResponse> getTopResponse(@Path("page") int page);

    @GET("anime/{id}")
    Observable<AnimeResponse> getAnimeResponse(@Path("id") int id);

    //old way of doing it
//    @GET("top/anime/{page}")
//    Call<AnimeTopResponse> getTopResponse(@Path("page") int page);
//
//    @GET("anime/{id}")
//    Call<AnimeResponse> getAnimeResponse(@Path("id") int id);
}
