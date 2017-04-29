package io.github.howiezuo.unsplash.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import java.lang.ref.WeakReference;

import io.github.howiezuo.unsplash.feature.main.MainListener;
import io.github.howiezuo.unsplash.model.Photo;


public class PhotoViewModel extends BaseObservable {

    public final ObservableField<Photo> photo = new ObservableField<>();

    private WeakReference<MainListener> mainListener;

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

}
