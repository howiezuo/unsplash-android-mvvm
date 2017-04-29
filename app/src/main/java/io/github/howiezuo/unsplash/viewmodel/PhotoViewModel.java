package io.github.howiezuo.unsplash.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import java.lang.ref.WeakReference;

import io.github.howiezuo.unsplash.api.Api;
import io.github.howiezuo.unsplash.api.PhotosService;
import io.github.howiezuo.unsplash.feature.main.MainListener;
import io.github.howiezuo.unsplash.model.Photo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class PhotoViewModel extends BaseObservable {

    private String id;

    public final ObservableField<Photo> photo = new ObservableField<>();

    private WeakReference<MainListener> mainListener;

    private Disposable disposable;

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoto(Photo photo) {
        this.photo.set(photo);
    }

    public void setMainListener(MainListener listener) {
        this.mainListener = new WeakReference<>(listener);
    }

    public void imageClicked() {
        if (mainListener != null && mainListener.get() != null) {
            mainListener.get().openPhotoDetail(photo.get().getId());
        }
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
        final Retrofit retrofit = Api.getRetrofit();
        PhotosService service = retrofit.create(PhotosService.class);

        disposable = service.getPhoto(id)
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
