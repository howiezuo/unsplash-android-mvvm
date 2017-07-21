package io.github.howiezuo.unsplash.api

import io.github.howiezuo.unsplash.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface PhotosService {

    @GET("photos")
    fun getPhotos(): Observable<List<Photo>>

    @GET("photos/{id}")
    fun getPhoto(@Path("id") id: String): Observable<Photo>

}
