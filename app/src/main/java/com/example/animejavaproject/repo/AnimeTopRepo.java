package com.example.animejavaproject.repo;

import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.model.AnimeTopResponse;

import retrofit2.Call;

public class AnimeTopRepo {

    private static AnimeTopRepo INSTANCE = null;

    private AnimeTopRepo() {

    }

    public Call<AnimeTopResponse> getTopResponse(int page) {
        return RetroFitInstance.getINSTANCE().getTopResponse(page);
    }
    public Call<AnimeResponse> getAnimeResponse(int id) {
        return RetroFitInstance.getINSTANCE().getAnimeResponse(id);
    }
    public static AnimeTopRepo getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AnimeTopRepo();
        return INSTANCE;
    }
}
