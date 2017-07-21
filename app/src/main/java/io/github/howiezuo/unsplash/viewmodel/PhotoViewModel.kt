package io.github.howiezuo.unsplash.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import io.github.howiezuo.unsplash.feature.main.MainListener
import io.github.howiezuo.unsplash.model.Photo
import java.lang.ref.WeakReference


class PhotoViewModel : BaseObservable() {

    val photo = ObservableField<Photo>()

    private var mainListener: WeakReference<MainListener>? = null

    fun setPhoto(photo: Photo) {
        this.photo.set(photo)
    }

    fun setMainListener(listener: MainListener) {
        mainListener = WeakReference(listener)
    }

    fun onImageClicked() {
        mainListener?.get()?.openPhotoDetail(photo.get().id)
    }

}
