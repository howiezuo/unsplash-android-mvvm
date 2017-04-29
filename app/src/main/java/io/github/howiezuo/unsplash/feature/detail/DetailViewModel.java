package io.github.howiezuo.unsplash.feature.detail;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.DaggerApiComponent;
import io.github.howiezuo.unsplash.api.PhotosService;
import io.github.howiezuo.unsplash.model.Photo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DetailViewModel extends BaseObservable {

    private String id;

    public final ObservableField<Photo> photo = new ObservableField<>();

    @Inject
    PhotosService photosService;

    private Disposable disposable;

    public DetailViewModel() {
        DaggerApiComponent.builder().build().inject(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void load() {
        fetchPhoto();
    }

    public void destroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void fetchPhoto() {
        disposable = photosService.getPhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Photo>() {
                    @Override
                    public void accept(Photo data) throws Exception {
                        photo.set(data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        return;
                    }
                });
    }
}
