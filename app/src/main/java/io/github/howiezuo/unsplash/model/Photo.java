package io.github.howiezuo.unsplash.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    String id;

    int width;

    int height;

    String color;

    int likes;

    @SerializedName("liked_by_user")
    boolean likedByUser;

    Urls urls;

    public String getId() {
        return id;
    }

    public Urls getUrls() {
        return urls;
    }
}
