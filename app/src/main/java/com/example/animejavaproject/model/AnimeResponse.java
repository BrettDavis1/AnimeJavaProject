package com.example.animejavaproject.model;

public class AnimeResponse {
    private String duration;
    private String rating;
    private String synopsis;

    public AnimeResponse(String duration, String rating, String synopsis) {
        this.duration = duration;
        this.rating = rating;
        this.synopsis = synopsis;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
