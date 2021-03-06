package com.example.animejavaproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeTopResponse {
    @SerializedName("request_hash")
    private String requestHash;
    @SerializedName("request_cached")
    private boolean requestCached;
    @SerializedName("request_cache_expiry")
    private int requestCacheExpiry;
    @SerializedName("top")
    private List<Top> top;


//    private String requestHash;
//    private boolean requestCached;
//    private int requestCacheExpiry;
//    private List<Top> top;

    public AnimeTopResponse(String requestHash, boolean requestCached, int requestCacheExpiry, List<Top> top) {
        this.requestHash = requestHash;
        this.requestCached = requestCached;
        this.requestCacheExpiry = requestCacheExpiry;
        this.top = top;
    }

    public String getRequestHash() {
        return requestHash;
    }

    public void setRequestHash(String requestHash) {
        this.requestHash = requestHash;
    }

    public boolean isRequestCached() {
        return requestCached;
    }

    public void setRequestCached(boolean requestCached) {
        this.requestCached = requestCached;
    }

    public int getRequestCacheExpiry() {
        return requestCacheExpiry;
    }

    public void setRequestCacheExpiry(int requestCacheExpiry) {
        this.requestCacheExpiry = requestCacheExpiry;
    }

    public List<Top> getTop() {
        return top;
    }

    public void setTop(List<Top> top) {
        this.top = top;
    }
}
