package io.github.howiezuo.unsplash.feature.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.DaggerApiComponent;
import io.github.howiezuo.unsplash.api.PhotosService;
import io.github.howiezuo.unsplash.model.Photo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends BaseObservable {

    public final ObservableList<Photo> photos = new ObservableArrayList<>();

    @Inject
    PhotosService photosService;

    private Disposable disposable;

    public MainViewModel() {
        DaggerApiComponent.builder().build().inject(this);
    }

    public void create() {
        fetchPhotos();
    }

    public void destroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void fetchPhotos() {
        disposable = photosService.getPhotos()
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
