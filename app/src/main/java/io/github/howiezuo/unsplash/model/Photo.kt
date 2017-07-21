package io.github.howiezuo.unsplash.model

import com.google.gson.annotations.SerializedName


data class Photo(
        val id: String,
        val width: Int,
        val height: Int,
        val color: String,
        val likes: Int,
        @SerializedName("liked_by_user") val likedByUser: Boolean,
        val urls: Urls
)
