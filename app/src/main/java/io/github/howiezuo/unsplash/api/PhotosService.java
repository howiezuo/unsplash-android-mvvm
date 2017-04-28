package io.github.howiezuo.unsplash.api;

import java.util.List;

import io.github.howiezuo.unsplash.model.Photo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotosService {

    @GET("photos")
    Observable<List<Photo>> getPhotos();

    @GET("photos/{id}")
    Observable<Photo> getPhoto(@Path("id") String id);

}
