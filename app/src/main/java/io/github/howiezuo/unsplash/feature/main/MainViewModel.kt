package io.github.howiezuo.unsplash.feature.main

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import io.github.howiezuo.unsplash.api.DaggerApiComponent
import io.github.howiezuo.unsplash.api.PhotosService
import io.github.howiezuo.unsplash.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel : BaseObservable() {

    val photos = ObservableArrayList<Photo>()

    @Inject
    lateinit var photosService: PhotosService

    private var disposable: Disposable? = null

    init {
        DaggerApiComponent.builder().build().inject(this)
    }

    private fun fetchPhotos() {
        disposable = photosService.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { t ->
                    photos.addAll(t)
                }, Consumer {
                    return@Consumer
                })
    }

    fun create() {
        fetchPhotos()
    }

    fun destroy() {
        disposable?.dispose()
    }
}
