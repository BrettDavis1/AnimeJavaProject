package com.example.animejavaproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Top implements Parcelable {
    @Json(name = "mal_id")
    private int malId;
    private int rank;
    private String title;
    @Json(name = "image_url")
    private String imageUrl;
    private String type;
    private Integer episodes;
    @Json(name = "start_date")
    private String startDate;
    @Json(name = "end_date")
    private String endDate; // can be null

    //Needed or wanted default value if data was empty
    public Top() {
        malId = 0;
        rank = 0;
        title = "NOTHING";
        imageUrl = "NOTHING";
        type = "NOTHING";
        episodes = 0;
        startDate = "NOTHING";
        endDate = "NOTHING";
    }
    public Top(int malId, int rank, String title, String imageUrl, String type, Integer episodes, String startDate, String endDate) {
        this.malId = malId;
        this.rank = rank;
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
        if(episodes.equals(null)) {
            episodes = 0;
        }
        this.episodes = episodes;
        this.startDate = startDate;
        if(endDate.equals(null)) {
            this.endDate = "Still Ongoing show";
        } else {
            this.endDate = endDate;
        }
    }
    public Top(Parcel in) {
        this.malId = in.readInt();
        this.rank = in.readInt();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.type = in.readString();
        this.episodes = in.readInt();
        this.startDate = in.readString();
        this.endDate = in.readString();
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEpisodes() {
        if(episodes == null) {
            episodes = 0;
        }
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if(endDate == null) {
            endDate = "";
        }
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(malId);
        dest.writeInt(rank);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(type);
        if(episodes != null) {
            dest.writeInt(episodes);
        } else {
            dest.writeInt(0);
        }
        dest.writeString(startDate);
        dest.writeString(endDate);
    }

    @Override
    public String toString() {
        return "Top{" +
                "malId=" + malId +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                ", episodes=" + episodes +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Top> CREATOR = new Parcelable.Creator<Top>() {

        @Override
        public Top createFromParcel(Parcel source) {
            return new Top(source);
        }

        @Override
        public Top[] newArray(int size) {
            return new Top[size];
        }
    };
}
