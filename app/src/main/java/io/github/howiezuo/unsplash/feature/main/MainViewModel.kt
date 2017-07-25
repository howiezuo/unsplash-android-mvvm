package io.github.howiezuo.unsplash.feature.main

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.github.howiezuo.unsplash.api.DaggerApiComponent
import io.github.howiezuo.unsplash.api.PhotosService
import io.github.howiezuo.unsplash.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel : BaseObservable() {

    val photos = ObservableArrayList<Photo>()

    val isRefreshing = ObservableBoolean(false)

    val errorText = ObservableField<String>();

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
                .subscribe({ t ->
                    photos.addAll(t)
                    isRefreshing.set(false)
                }, { t ->
                    errorText.set(t.localizedMessage)
                    isRefreshing.set(false)
                })
    }

    fun refresh() {
        isRefreshing.set(true)
        fetchPhotos()
    }

    fun create() {
        fetchPhotos()
    }

    fun destroy() {
        disposable?.dispose()
    }
}
