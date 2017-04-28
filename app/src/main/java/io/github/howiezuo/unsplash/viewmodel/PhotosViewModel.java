package io.github.howiezuo.unsplash.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import io.github.howiezuo.unsplash.api.Api;
import io.github.howiezuo.unsplash.api.PhotosService;
import io.github.howiezuo.unsplash.model.Photo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class PhotosViewModel extends BaseObservable {

    public final ObservableList<Photo> photos = new ObservableArrayList<>();

    private Disposable disposable;

    public void load() {
        fetchPhotos();
    }

    public void destroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void fetchPhotos() {
        Retrofit retrofit = Api.getRetrofit();
        PhotosService service = retrofit.create(PhotosService.class);

        disposable = service.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Photo>>() {
                    @Override
                    public void accept(List<Photo> list) throws Exception {
                        photos.addAll(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        return;
                    }
                });
    }
}
